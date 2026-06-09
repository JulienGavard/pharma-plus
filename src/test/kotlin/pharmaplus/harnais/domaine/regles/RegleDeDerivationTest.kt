package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.TableDeDerivation
import pharmaplus.harnais.domaine.epic
import pharmaplus.harnais.domaine.specs
import kotlin.test.Test
import kotlin.test.assertTrue

class RegleDeDerivationTest {

    private val regle = RegleDeDerivation()

    @Test
    fun `correspondance exacte ne produit aucune violation`() {
        val violations = regle.verifier(
            specs(table = TableDeDerivation(mapOf(1 to "epic-1")), epics = listOf(epic(id = "epic-1"))),
        )
        assertTrue(violations.isEmpty())
    }

    @Test
    fun `epic dans la table mais sans fichier`() {
        val violations = regle.verifier(
            specs(
                table = TableDeDerivation(mapOf(1 to "epic-1", 2 to "epic-2")),
                epics = listOf(epic(id = "epic-1")),
            ),
        )
        assertTrue(violations.any { it.message.contains("epic-2") && it.message.contains("sans fichier") })
    }

    @Test
    fun `epic en fichier mais absent de la table`() {
        val violations = regle.verifier(
            specs(
                table = TableDeDerivation(mapOf(1 to "epic-1")),
                epics = listOf(
                    epic(id = "epic-1"),
                    epic(id = "epic-5", slug = "autre-epic", titre = "Autre épic"),
                ),
            ),
        )
        assertTrue(violations.any { it.message.contains("epic-5") && it.message.contains("absent de la table") })
    }
}
