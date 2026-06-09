package pharmaplus.harnais.domaine

/** Entité : une feature telle que lue dans docs/features/<epic>/ — capacité testable (gdr-l3-002). */
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
}
