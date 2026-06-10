package pharmaplus.harnais.infrastructure

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LecteurDeFrontmatterTest {

    private val lecteur = LecteurDeFrontmatter()

    @Test
    fun `absence de frontmatter renvoie null`() {
        assertNull(lecteur.lire("# Titre\n\nDu contenu, sans frontmatter."))
    }

    @Test
    fun `frontmatter non fermee renvoie null`() {
        assertNull(lecteur.lire("---\nid: epic-1\n(jamais refermee)"))
    }

    @Test
    fun `lit les cles scalaires`() {
        val fm = lecteur.lire("---\nid: epic-1\ntitre: Inventaire du stock\n---\n# corps")
        assertEquals("epic-1", fm?.get("id"))
        assertEquals("Inventaire du stock", fm?.get("titre"))
    }

    @Test
    fun `lit une liste en notation flow`() {
        val fm = lecteur.lire("---\nsource_prd: [CS-4, CT-1, CB-1]\n---\n")
        assertEquals(listOf("CS-4", "CT-1", "CB-1"), fm?.get("source_prd"))
    }

    @Test
    fun `retire les guillemets autour des valeurs`() {
        val fm = lecteur.lire("---\nslug: \"scan-code-barres\"\n---\n")
        assertEquals("scan-code-barres", fm?.get("slug"))
    }
}
