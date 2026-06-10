package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.epic
import pharmaplus.harnais.domaine.feature
import pharmaplus.harnais.domaine.specs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RegleDeStructureTest {

    private val regle = RegleDeStructure()

    @Test
    fun `agrege les violations structurelles des epics et des features`() {
        val violations = regle.verifier(
            specs(
                epics = listOf(epic(frontmatterPresente = false)),
                features = listOf(feature(frontmatterPresente = false)),
            ),
        )
        assertEquals(2, violations.size)
    }

    @Test
    fun `aucune violation quand tout est conforme`() {
        assertTrue(regle.verifier(specs(epics = listOf(epic()), features = listOf(feature()))).isEmpty())
    }
}
