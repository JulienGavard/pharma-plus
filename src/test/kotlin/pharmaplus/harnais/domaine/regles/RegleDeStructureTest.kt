package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.epic
import pharmaplus.harnais.domaine.specs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RegleDeStructureTest {

    private val regle = RegleDeStructure()

    @Test
    fun `un epic conforme ne produit aucune violation`() {
        assertTrue(regle.verifier(specs(epics = listOf(epic()))).isEmpty())
    }

    @Test
    fun `frontmatter absente est signalee une seule fois`() {
        val violations = regle.verifier(specs(epics = listOf(epic(frontmatterPresente = false))))
        assertEquals(1, violations.size)
        assertTrue(violations.first().message.contains("frontmatter absente"))
    }

    @Test
    fun `priorite invalide`() {
        val violations = regle.verifier(specs(epics = listOf(epic(priorite = "urgente"))))
        assertTrue(violations.any { it.message.contains("priorite invalide") })
    }

    @Test
    fun `lot non entier`() {
        val violations = regle.verifier(specs(epics = listOf(epic(lot = "un"))))
        assertTrue(violations.any { it.message.contains("lot non entier") })
    }

    @Test
    fun `id epic malforme`() {
        val violations = regle.verifier(specs(epics = listOf(epic(id = "epic-X"))))
        assertTrue(violations.any { it.message.contains("id épic invalide") })
    }
}
