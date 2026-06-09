package pharmaplus.harnais.domaine

/** Résultat d'une validation : l'ensemble des violations constatées. */
data class RapportDeConformite(val violations: List<Violation>) {

    val estConforme: Boolean get() = violations.isEmpty()

    val total: Int get() = violations.size

    fun parCategorie(): Map<CategorieViolation, List<Violation>> =
        violations.groupBy { it.categorie }
}
