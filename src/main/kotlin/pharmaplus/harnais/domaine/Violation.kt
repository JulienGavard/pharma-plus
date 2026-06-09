package pharmaplus.harnais.domaine

/** Catégorie d'une violation, dans l'ordre d'affichage du rapport. */
enum class CategorieViolation(val libelle: String) {
    STRUCTURE("Structurel"),
    REFERENCE("Référentiel"),
    COUVERTURE("Couverture"),
    DERIVATION("Dérivation"),
    NOMMAGE("Nommage"),
    UNICITE("Unicité"),
}

/** Une infraction à une règle de gouvernance, constatée sur un artefact. */
data class Violation(val categorie: CategorieViolation, val message: String)
