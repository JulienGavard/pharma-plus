package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.IdentifiantPrd
import pharmaplus.harnais.domaine.epic
import pharmaplus.harnais.domaine.feature
import pharmaplus.harnais.domaine.prd
import pharmaplus.harnais.domaine.specs
import kotlin.test.Test
import kotlin.test.assertTrue

class RegleDeReferenceTest {

    private val regle = RegleDeReference()

    @Test
    fun `delegue la reference aux entites (source_prd inconnu)`() {
        val violations = regle.verifier(
            specs(prd = prd(IdentifiantPrd("OBJ")), epics = listOf(epic(sourcesPrd = listOf("CS-99")))),
        )
        assertTrue(violations.any { it.message.contains("CS-99") })
    }

    @Test
    fun `l'epic parent d'une feature doit exister`() {
        val violations = regle.verifier(
            specs(prd = prd(IdentifiantPrd("CS-4")), features = listOf(feature(id = "feat-9.1", epic = "epic-9"))),
        )
        assertTrue(violations.any { it.message.contains("epic parent") })
    }

    @Test
    fun `references coherentes ne produisent aucune violation`() {
        val violations = regle.verifier(
            specs(
                prd = prd(IdentifiantPrd("OBJ"), IdentifiantPrd("CS-4")),
                epics = listOf(epic(id = "epic-1")),
                features = listOf(feature(id = "feat-1.1", epic = "epic-1")),
            ),
        )
        assertTrue(violations.isEmpty())
    }
}
