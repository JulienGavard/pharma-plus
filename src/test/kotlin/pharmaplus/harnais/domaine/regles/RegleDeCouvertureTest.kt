package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.IdentifiantPrd
import pharmaplus.harnais.domaine.epic
import pharmaplus.harnais.domaine.feature
import pharmaplus.harnais.domaine.prd
import pharmaplus.harnais.domaine.specs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RegleDeCouvertureTest {

    private val regle = RegleDeCouverture()

    @Test
    fun `tout couvrable couvert par epic et feature ne produit aucune violation`() {
        val violations = regle.verifier(
            specs(
                prd = prd(IdentifiantPrd("OBJ"), IdentifiantPrd("CS-1")),
                epics = listOf(epic(sourcesPrd = listOf("OBJ", "CS-1"))),
                features = listOf(feature(sourcesPrd = listOf("OBJ", "CS-1"))),
            ),
        )
        assertTrue(violations.isEmpty())
    }

    @Test
    fun `un element non couvert produit deux violations (epic et feature)`() {
        val violations = regle.verifier(specs(prd = prd(IdentifiantPrd("CS-9"))))
        assertEquals(2, violations.size)
        assertTrue(violations.all { it.message.startsWith("CS-9") })
    }

    @Test
    fun `un element exempte n'est pas soumis a couverture`() {
        val violations = regle.verifier(
            specs(prd = prd(IdentifiantPrd("CL-4", exempteeDeCouverture = true))),
        )
        assertTrue(violations.isEmpty())
    }
}
