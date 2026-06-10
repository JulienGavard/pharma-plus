package pharmaplus.harnais.domaine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class EpicTest {

    @Test
    fun `le numero est extrait de l'id`() {
        assertEquals(3, epic(id = "epic-3").numero)
    }

    @Test
    fun `le numero est null si l'id est absent ou malforme`() {
        assertNull(epic(id = "epic-X").numero)
        assertNull(epic(id = null).numero)
    }

    // --- Structure ---

    @Test
    fun `un epic conforme n'a aucune violation structurelle`() {
        assertTrue(epic().violationsStructurelles().isEmpty())
    }

    @Test
    fun `frontmatter absente est signalee une seule fois`() {
        val v = epic(frontmatterPresente = false).violationsStructurelles()
        assertEquals(1, v.size)
        assertTrue(v.first().message.contains("frontmatter absente"))
    }

    @Test
    fun `priorite invalide`() {
        assertTrue(epic(priorite = "urgente").violationsStructurelles().any { it.message.contains("priorite invalide") })
    }

    @Test
    fun `lot non entier`() {
        assertTrue(epic(lot = "un").violationsStructurelles().any { it.message.contains("lot non entier") })
    }

    @Test
    fun `id epic malforme`() {
        assertTrue(epic(id = "epic-X").violationsStructurelles().any { it.message.contains("id épic invalide") })
    }

    // --- Nommage ---

    @Test
    fun `un epic bien nomme n'a aucune violation de nommage`() {
        assertTrue(epic().violationsDeNommage().isEmpty())
    }

    @Test
    fun `slug different de slugify(titre)`() {
        val v = epic(titre = "Inventaire du stock", slug = "inventaire", nomFichier = "inventaire").violationsDeNommage()
        assertTrue(v.any { it.message.contains("slugify(titre)") })
    }

    @Test
    fun `nom de fichier different du slug`() {
        val v = epic(slug = "inventaire-du-stock", nomFichier = "autre").violationsDeNommage()
        assertTrue(v.any { it.message.contains("nom de fichier") })
    }

    // --- Référence ---

    @Test
    fun `source_prd absent du PRD est signale`() {
        val v = epic(sourcesPrd = listOf("CS-99")).violationsDeReference(prd(IdentifiantPrd("OBJ")))
        assertTrue(v.any { it.message.contains("CS-99") && it.message.contains("absent du PRD") })
    }

    @Test
    fun `source_prd present ne produit aucune violation de reference`() {
        assertTrue(epic(sourcesPrd = listOf("OBJ")).violationsDeReference(prd(IdentifiantPrd("OBJ"))).isEmpty())
    }
}
