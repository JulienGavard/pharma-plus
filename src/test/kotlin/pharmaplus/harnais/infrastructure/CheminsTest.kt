package pharmaplus.harnais.infrastructure

import kotlin.io.path.createTempDirectory
import kotlin.io.path.div
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CheminsTest {

    @Test
    fun `resout un chemin defini dans le fichier`() {
        val racine = createTempDirectory()
        (racine / "chemins.properties").writeText("PRD=conception/registres/produit/PRD.md\n")

        assertTrue(Chemins(racine).prd.endsWith("PRD.md"))
    }

    @Test
    fun `echoue si le fichier de chemins est absent`() {
        val racine = createTempDirectory()

        assertFailsWith<IllegalArgumentException> { Chemins(racine) }
    }

    @Test
    fun `echoue si une cle est absente`() {
        val racine = createTempDirectory()
        (racine / "chemins.properties").writeText("PRD=x/PRD.md\n")

        assertFailsWith<IllegalStateException> { Chemins(racine).tableDerivation }
    }
}
