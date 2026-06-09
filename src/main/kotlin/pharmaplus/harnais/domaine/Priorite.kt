package pharmaplus.harnais.domaine

/** Value object : la priorité d'un épic ou d'une feature (gdr-l2-007, gdr-l3-002). */
enum class Priorite(val texte: String) {
    CRITIQUE("critique"),
    HAUTE("haute"),
    MOYENNE("moyenne"),
    BASSE("basse");

    companion object {
        fun estValide(texte: String?): Boolean = entries.any { it.texte == texte }
    }
}
