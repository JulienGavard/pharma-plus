package pharmaplus.harnais.domaine

/**
 * Feature telle que lue dans le registre — capacité testable (gdr-l3-002).
 * Elle porte sa propre validation : modèle de domaine non anémique (adr-003).
 */
data class Feature(
    val chemin: String,
    val nomFichier: String,
    val dossier: String,
    val frontmatterPresente: Boolean,
    val id: String?,
    val epic: String?,
    val slug: String?,
    val titre: String?,
    val priorite: String?,
    val lot: String?,
    val sourcesPrd: List<String>,
) {
    /** Couple (numéro d'épic, numéro de feature) extrait de l'id « feat-N.M », ou null. */
    val numeros: Pair<Int, Int>?
        get() {
            val reste = id?.takeIf { it.startsWith("feat-") }?.removePrefix("feat-") ?: return null
            val parts = reste.split(".")
            if (parts.size != 2) return null
            val n = parts[0].toIntOrNull() ?: return null
            val m = parts[1].toIntOrNull() ?: return null
            return n to m
        }

    /** Violations de structure : frontmatter, champs obligatoires, énumération, format d'id. */
    fun violationsStructurelles(): List<Violation> {
        if (!frontmatterPresente) return listOf(structure("frontmatter absente"))
        val v = mutableListOf<Violation>()
        if (id.isNullOrBlank()) v += structure("champ « id » manquant/vide")
        if (epic.isNullOrBlank()) v += structure("champ « epic » manquant/vide")
        if (slug.isNullOrBlank()) v += structure("champ « slug » manquant/vide")
        if (titre.isNullOrBlank()) v += structure("champ « titre » manquant/vide")
        if (priorite.isNullOrBlank()) v += structure("champ « priorite » manquant/vide")
        if (lot.isNullOrBlank()) v += structure("champ « lot » manquant/vide")
        if (sourcesPrd.isEmpty()) v += structure("champ « source_prd » manquant/vide")
        if (priorite != null && !Priorite.estValide(priorite)) v += structure("priorite invalide « $priorite »")
        if (!lot.isNullOrBlank() && lot.toIntOrNull() == null) v += structure("lot non entier « $lot »")
        if (id != null && numeros == null) v += structure("id feature invalide « $id » (attendu feat-N.M)")
        return v
    }

    /** Violations de nommage intrinsèques : slug == slugify(titre), et nom de fichier == N-M-slug. */
    fun violationsDeNommage(): List<Violation> {
        val v = mutableListOf<Violation>()
        if (titre != null && slug != null) {
            val attendu = Slug.depuisTitre(titre).valeur
            if (slug != attendu) v += nommage("slug « $slug » ≠ slugify(titre) « $attendu »")
        }
        val numeros = numeros
        if (numeros != null && slug != null) {
            val attendu = "${numeros.first}-${numeros.second}-$slug"
            if (nomFichier != attendu) v += nommage("nom de fichier « $nomFichier » ≠ « $attendu »")
        }
        return v
    }

    /** Violations de référence intrinsèques : source_prd ∈ PRD, et cohérence id ↔ epic (feat-N.M → epic-N). */
    fun violationsDeReference(prd: Prd): List<Violation> {
        val v = mutableListOf<Violation>()
        v += sourcesPrd.filterNot { prd.contient(it) }.map { reference("source_prd « $it » absent du PRD") }
        val n = numeros?.first
        if (n != null && epic != "epic-$n") {
            v += reference("id « $id » incohérent avec epic « $epic » (attendu epic-$n)")
        }
        return v
    }

    private fun structure(message: String) = Violation(CategorieViolation.STRUCTURE, "$chemin: $message")
    private fun nommage(message: String) = Violation(CategorieViolation.NOMMAGE, "$chemin: $message")
    private fun reference(message: String) = Violation(CategorieViolation.REFERENCE, "$chemin: $message")
}
