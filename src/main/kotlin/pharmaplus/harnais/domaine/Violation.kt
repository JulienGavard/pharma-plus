package pharmaplus.harnais.domaine

/** Catégorie d'une violation, dans l'ordre d'affichage du rapport. */
enum class CategorieViolation(val libelle: String) {
    STRUCTURE("Structurel"),
    REFERENCE("Référentiel"),
    COUVERTURE("Couverture"),
    DERIVATION("Dérivation"),
    NOMMAGE("Nommage"),
    UNICITE("Unicité"),
    ;

    /** Fabrique une violation de cette catégorie. */
    fun violation(message: String): Violation = Violation(this, message)

    /** Fabrique une violation préfixée par le chemin de l'artefact concerné. */
    fun violation(chemin: String, message: String): Violation = Violation(this, "$chemin: $message")
}

/** Une infraction à une règle de gouvernance, constatée sur un artefact. */
data class Violation(val categorie: CategorieViolation, val message: String)
