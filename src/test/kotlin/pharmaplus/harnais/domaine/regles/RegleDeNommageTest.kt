package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.epic
import pharmaplus.harnais.domaine.specs
import kotlin.test.Test
import kotlin.test.assertTrue

class RegleDeNommageTest {

    private val regle = RegleDeNommage()

    @Test
    fun `un epic bien nomme ne produit aucune violation`() {
        assertTrue(regle.verifier(specs(epics = listOf(epic()))).isEmpty())
    }

    @Test
    fun `slug different de slugify(titre)`() {
        val incoherent = epic(titre = "Inventaire du stock", slug = "inventaire", nomFichier = "inventaire")
        val violations = regle.verifier(specs(epics = listOf(incoherent)))
        assertTrue(violations.any { it.message.contains("slugify(titre)") })
    }

    @Test
    fun `nom de fichier different du slug`() {
        val incoherent = epic(slug = "inventaire-du-stock", nomFichier = "autre")
        val violations = regle.verifier(specs(epics = listOf(incoherent)))
        assertTrue(violations.any { it.message.contains("nom de fichier") })
    }
}
