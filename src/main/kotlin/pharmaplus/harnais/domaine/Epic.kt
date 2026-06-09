package pharmaplus.harnais.domaine

/** Entité : un épic tel que lu dans docs/epics/ — un axe de valeur (gdr-l2-007). */
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
        get() = id?.takeIf { it.startsWith("epic-") }
            ?.removePrefix("epic-")
            ?.toIntOrNull()
}
