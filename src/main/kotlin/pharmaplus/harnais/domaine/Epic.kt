package pharmaplus.harnais.domaine

/**
 * Épic tel que lu dans le registre — un axe de valeur (gdr-l2-007).
 * Il porte sa propre validation : modèle de domaine non anémique (adr-003).
 */
data class Epic(
    val chemin: String,
    val nomFichier: String,
    val frontmatterPresente: Boolean,
    val id: String?,
    val slug: String?,
    val titre: String?,
    val priorite: String?,
    val lot: String?,
    val sourcesPrd: List<String>,
) {
    /** Numéro N extrait de l'id « epic-N », ou null si l'id est absent/malformé. */
    val numero: Int?
        get() = id?.takeIf { it.startsWith("epic-") }?.removePrefix("epic-")?.toIntOrNull()

    /** Violations de structure : frontmatter, champs obligatoires, énumération, format d'id. */
    fun violationsStructurelles(): List<Violation> {
        if (!frontmatterPresente) return listOf(structure("frontmatter absente"))
        val v = mutableListOf<Violation>()
        if (id.isNullOrBlank()) v += structure("champ « id » manquant/vide")
        if (slug.isNullOrBlank()) v += structure("champ « slug » manquant/vide")
        if (titre.isNullOrBlank()) v += structure("champ « titre » manquant/vide")
        if (priorite.isNullOrBlank()) v += structure("champ « priorite » manquant/vide")
        if (lot.isNullOrBlank()) v += structure("champ « lot » manquant/vide")
        if (sourcesPrd.isEmpty()) v += structure("champ « source_prd » manquant/vide")
        if (priorite != null && !Priorite.estValide(priorite)) v += structure("priorite invalide « $priorite »")
        if (!lot.isNullOrBlank() && lot.toIntOrNull() == null) v += structure("lot non entier « $lot »")
        if (id != null && numero == null) v += structure("id épic invalide « $id » (attendu epic-N)")
        return v
    }

    /** Violations de nommage : slug == slugify(titre) == nom de fichier. */
    fun violationsDeNommage(): List<Violation> {
        val v = mutableListOf<Violation>()
        if (titre != null && slug != null) {
            val attendu = Slug.depuisTitre(titre).valeur
            if (slug != attendu) v += nommage("slug « $slug » ≠ slugify(titre) « $attendu »")
        }
        if (slug != null && nomFichier != slug) v += nommage("nom de fichier « $nomFichier » ≠ slug « $slug »")
        return v
    }

    /** Violations de référence intrinsèques : chaque source_prd existe dans le PRD. */
    fun violationsDeReference(prd: Prd): List<Violation> =
        sourcesPrd.filterNot { prd.contient(it) }.map { reference("source_prd « $it » absent du PRD") }

    private fun structure(message: String) = Violation(CategorieViolation.STRUCTURE, "$chemin: $message")
    private fun nommage(message: String) = Violation(CategorieViolation.NOMMAGE, "$chemin: $message")
    private fun reference(message: String) = Violation(CategorieViolation.REFERENCE, "$chemin: $message")
}
