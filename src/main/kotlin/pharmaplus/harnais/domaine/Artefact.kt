package pharmaplus.harnais.domaine

/**
 * Un artefact de spécification identifié par son `chemin`. Fournit la construction
 * de ses propres violations, préfixées par ce chemin — factorisation partagée par
 * les entités auto-validantes (adr-003).
 */
interface Artefact {
    val chemin: String

    fun structure(message: String): Violation = CategorieViolation.STRUCTURE.violation(chemin, message)
    fun nommage(message: String): Violation = CategorieViolation.NOMMAGE.violation(chemin, message)
    fun reference(message: String): Violation = CategorieViolation.REFERENCE.violation(chemin, message)
}
