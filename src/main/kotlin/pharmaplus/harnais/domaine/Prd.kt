package pharmaplus.harnais.domaine

/** Entité : le PRD, vu par le harnais comme l'ensemble de ses identifiants stables. */
data class Prd(val identifiants: List<IdentifiantPrd>) {

    fun contient(code: String): Boolean = identifiants.any { it.code == code }

    fun codes(): Set<String> = identifiants.map { it.code }.toSet()

    fun codesCouvrables(): Set<String> =
        identifiants.filter { it.estCouvrable }.map { it.code }.toSet()
}
