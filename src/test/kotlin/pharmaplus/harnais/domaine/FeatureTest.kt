package pharmaplus.harnais.domaine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class FeatureTest {

    @Test
    fun `les numeros sont extraits de l'id`() {
        assertEquals(1 to 2, feature(id = "feat-1.2").numeros)
    }

    @Test
    fun `les numeros sont null si l'id est malforme`() {
        assertNull(feature(id = "feat-1").numeros)
        assertNull(feature(id = "feat-a.b").numeros)
        assertNull(feature(id = null).numeros)
    }

    // --- Structure ---

    @Test
    fun `une feature conforme n'a aucune violation structurelle`() {
        assertTrue(feature().violationsStructurelles().isEmpty())
    }

    @Test
    fun `champ epic manquant`() {
        assertTrue(feature(epic = null).violationsStructurelles().any { it.message.contains("« epic »") })
    }

    @Test
    fun `id feature malforme`() {
        assertTrue(feature(id = "feat-1").violationsStructurelles().any { it.message.contains("id feature invalide") })
    }

    // --- Nommage ---

    @Test
    fun `une feature bien nommee n'a aucune violation de nommage`() {
        assertTrue(feature().violationsDeNommage().isEmpty())
    }

    @Test
    fun `nom de fichier different de N-M-slug`() {
        assertTrue(feature(nomFichier = "faux").violationsDeNommage().any { it.message.contains("nom de fichier") })
    }

    // --- Référence ---

    @Test
    fun `source_prd absent du PRD est signale`() {
        val v = feature(sourcesPrd = listOf("CS-99")).violationsDeReference(prd(IdentifiantPrd("OBJ")))
        assertTrue(v.any { it.message.contains("CS-99") })
    }

    @Test
    fun `id incoherent avec l'epic parent`() {
        val v = feature(id = "feat-1.1", epic = "epic-2").violationsDeReference(prd(IdentifiantPrd("CS-4")))
        assertTrue(v.any { it.message.contains("incohérent avec epic") })
    }
}
