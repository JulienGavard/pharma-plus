package pharmaplus.harnais.infrastructure

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.RapportDeConformite
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertTrue

class RapportConsoleTest {

    private val specsVides = Specifications(prd = null, table = null, epics = emptyList(), features = emptyList())

    private fun rendu(rapport: RapportDeConformite): String {
        val tampon = ByteArrayOutputStream()
        RapportConsole(PrintStream(tampon, true, Charsets.UTF_8)).afficher(specsVides, rapport)
        return tampon.toString(Charsets.UTF_8)
    }

    @Test
    fun `un rapport conforme affiche le succes`() {
        assertTrue(rendu(RapportDeConformite(emptyList())).contains("[OK] Conforme"))
    }

    @Test
    fun `un rapport en echec groupe par categorie, compte et totalise`() {
        val rapport = RapportDeConformite(
            listOf(
                Violation(CategorieViolation.COUVERTURE, "OBJ: couvert par aucun épic"),
                Violation(CategorieViolation.COUVERTURE, "OBJ: couvert par aucune feature"),
                Violation(CategorieViolation.DERIVATION, "épic « epic-1 » présent dans la table mais sans fichier"),
            ),
        )
        val sortie = rendu(rapport)
        assertTrue(sortie.contains("[ECHEC] Couverture (2)"))
        assertTrue(sortie.contains("[ECHEC] Dérivation (1)"))
        assertTrue(sortie.contains("Total : 3 violation(s)."))
    }
}
