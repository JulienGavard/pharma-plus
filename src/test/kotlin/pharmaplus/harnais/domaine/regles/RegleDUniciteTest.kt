package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.epic
import pharmaplus.harnais.domaine.specs
import kotlin.test.Test
import kotlin.test.assertTrue

class RegleDUniciteTest {

    private val regle = RegleDUnicite()

    @Test
    fun `des identifiants uniques ne produisent aucune violation`() {
        val epics = listOf(
            epic(id = "epic-1", slug = "a", nomFichier = "a", titre = "A"),
            epic(id = "epic-2", slug = "b", nomFichier = "b", titre = "B"),
        )
        assertTrue(regle.verifier(specs(epics = epics)).isEmpty())
    }

    @Test
    fun `un id d'epic duplique est signale`() {
        val epics = listOf(
            epic(id = "epic-1", slug = "a", nomFichier = "a", titre = "A"),
            epic(id = "epic-1", slug = "b", nomFichier = "b", titre = "B"),
        )
        val violations = regle.verifier(specs(epics = epics))
        assertTrue(violations.any { it.message.contains("id épic dupliqué") })
    }
}
