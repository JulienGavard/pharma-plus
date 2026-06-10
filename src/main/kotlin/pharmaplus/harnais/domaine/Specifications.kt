package pharmaplus.harnais.domaine

/**
 * Racine d'agrégat : l'ensemble cohérent des spécifications à un instant donné
 * (PRD, table, épics, features). Elle compose la validation intrinsèque de ses
 * membres et porte les **invariants transverses** — ceux qui lient plusieurs
 * objets et n'appartiennent à aucune entité isolée (adr-004).
 */
data class Specifications(
    val prd: Prd?,
    val table: TableDeDerivation?,
    val epics: List<Epic>,
    val features: List<Feature>,
) {
    /** Vérifie la conformité de l'ensemble et produit le rapport. */
    fun verifierConformite(): RapportDeConformite =
        RapportDeConformite(
            violationsIntrinseques() +
                violationsDeCouverture() +
                violationsDeDerivation() +
                violationsDUnicite() +
                violationsDeParente() +
                violationsDeDossier(),
        )

    private val prdConnu: Prd get() = prd ?: Prd(emptyList())

    /** Validation que chaque entité fait sur elle-même (déléguée). */
    private fun violationsIntrinseques(): List<Violation> =
        epics.flatMap { it.violationsStructurelles() + it.violationsDeNommage() + it.violationsDeReference(prdConnu) } +
            features.flatMap { it.violationsStructurelles() + it.violationsDeNommage() + it.violationsDeReference(prdConnu) }

    /** Chaque élément couvrable du PRD est porté par ≥ 1 épic ET ≥ 1 feature (gdr-l1-006). */
    private fun violationsDeCouverture(): List<Violation> {
        val prd = prd ?: return emptyList()
        val parEpics = epics.flatMap { it.sourcesPrd }.toSet()
        val parFeatures = features.flatMap { it.sourcesPrd }.toSet()
        val v = mutableListOf<Violation>()
        for (code in prd.codesCouvrables().sorted()) {
            if (code !in parEpics) v += couverture("$code: couvert par aucun épic")
            if (code !in parFeatures) v += couverture("$code: couvert par aucune feature")
        }
        return v
    }

    /** Correspondance exacte entre la table de dérivation et les fichiers d'épics (gdr-l2-001). */
    private fun violationsDeDerivation(): List<Violation> {
        val table = table ?: return emptyList()
        val idsTable = table.identifiants()
        val idsFichiers = epics.mapNotNull { it.id }.toSet()
        val v = mutableListOf<Violation>()
        for (id in (idsTable - idsFichiers).sorted()) v += derivation("épic « $id » présent dans la table mais sans fichier")
        for (id in (idsFichiers - idsTable).sorted()) v += derivation("épic « $id » présent en fichier mais absent de la table")
        for ((numero, id) in table.epics) {
            if (id != "epic-$numero") v += derivation("table: numéro $numero incohérent avec id « $id »")
        }
        return v
    }

    /** Pas deux artefacts partageant le même id ou slug. */
    private fun violationsDUnicite(): List<Violation> {
        val v = mutableListOf<Violation>()
        v += doublons(epics.mapNotNull { it.id }) { "id épic dupliqué « $it »" }
        v += doublons(epics.mapNotNull { it.slug }) { "slug épic dupliqué « $it »" }
        v += doublons(features.mapNotNull { it.id }) { "id feature dupliqué « $it »" }
        v += doublons(
            features.filter { it.epic != null && it.slug != null }.map { "${it.epic}/${it.slug}" },
        ) { "slug feature dupliqué dans l'épic « $it »" }
        return v
    }

    /** L'épic parent de chaque feature existe (en fichier ou dans la table). */
    private fun violationsDeParente(): List<Violation> {
        val idsEpics = epics.mapNotNull { it.id }.toSet() + (table?.identifiants() ?: emptySet())
        return features.mapNotNull { f ->
            f.epic?.takeIf { it !in idsEpics }?.let { reference("${f.chemin}: epic parent « $it » inexistant") }
        }
    }

    /** Le dossier d'une feature vaut le slug de son épic parent. */
    private fun violationsDeDossier(): List<Violation> {
        val slugParEpic = epics.filter { it.id != null }.associate { it.id!! to it.slug }
        return features.mapNotNull { f ->
            val slugEpic = slugParEpic[f.epic]
            if (slugEpic != null && f.dossier != slugEpic) {
                nommage("${f.chemin}: dossier « ${f.dossier} » ≠ slug de l'épic « $slugEpic »")
            } else {
                null
            }
        }
    }

    private fun doublons(valeurs: List<String>, message: (String) -> String): List<Violation> =
        valeurs.groupingBy { it }.eachCount().filter { it.value > 1 }.keys.map { unicite(message(it)) }

    private fun couverture(m: String) = Violation(CategorieViolation.COUVERTURE, m)
    private fun derivation(m: String) = Violation(CategorieViolation.DERIVATION, m)
    private fun unicite(m: String) = Violation(CategorieViolation.UNICITE, m)
    private fun reference(m: String) = Violation(CategorieViolation.REFERENCE, m)
    private fun nommage(m: String) = Violation(CategorieViolation.NOMMAGE, m)
}
