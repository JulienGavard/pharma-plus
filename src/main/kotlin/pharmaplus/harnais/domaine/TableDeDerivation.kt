package pharmaplus.harnais.domaine

/** Le contrat de génération : numéro d'épic → identifiant (gdr-l2-001, gdr-l2-005). */
data class TableDeDerivation(val epics: Map<Int, String>) {

    fun identifiants(): Set<String> = epics.values.toSet()
}
