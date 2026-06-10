package pharmaplus.harnais.infrastructure

import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createTempDirectory
import kotlin.io.path.div
import kotlin.io.path.writeText
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DepotDeSpecificationsTest {

    /** Construit un dépôt de test complet (chemins + PRD + table + un épic + une feature). */
    private fun racineComplete(): Path {
        val racine = createTempDirectory()
        (racine / "chemins.properties").writeText(
            """
            PRD=produit/PRD.md
            TABLE_DERIVATION=specs/table.md
            EPICS=specs/epics
            FEATURES=specs/features
            """.trimIndent(),
        )
        (racine / "produit").createDirectories()
        (racine / "produit" / "PRD.md").writeText(
            """
            `[OBJ]` objectif
            `[CS-1]` critère
            `[CL-4]` interdiction *(non soumise à l'obligation de couverture)*
            """.trimIndent(),
        )
        (racine / "specs").createDirectories()
        (racine / "specs" / "table.md").writeText("| 1 | epic-1 | Inventaire du stock | critique | 1 |\n")

        val epics = (racine / "specs" / "epics").apply { createDirectories() }
        (epics / "inventaire-du-stock.md").writeText(
            """
            ---
            id: epic-1
            slug: inventaire-du-stock
            titre: Inventaire du stock
            priorite: critique
            lot: 1
            source_prd: [OBJ, CS-1]
            ---
            # corps
            """.trimIndent(),
        )

        val features = (racine / "specs" / "features" / "inventaire-du-stock").apply { createDirectories() }
        (features / "1-1-scan-code-barres.md").writeText(
            """
            ---
            id: feat-1.1
            epic: epic-1
            slug: scan-code-barres
            titre: Scan code-barres
            priorite: haute
            lot: 1
            source_prd: [CS-1]
            ---
            """.trimIndent(),
        )
        return racine
    }

    @Test
    fun `charge les identifiants du PRD et detecte les exemptes`() {
        val prd = DepotDeSpecifications(racineComplete()).charger().prd!!
        assertTrue(prd.contient("OBJ"))
        assertTrue(prd.contient("CL-4"))
        assertTrue("OBJ" in prd.codesCouvrables())
        assertTrue("CL-4" !in prd.codesCouvrables()) // exempté
    }

    @Test
    fun `charge la table de derivation`() {
        val table = DepotDeSpecifications(racineComplete()).charger().table!!
        assertEquals(mapOf(1 to "epic-1"), table.epics)
    }

    @Test
    fun `charge un epic depuis sa frontmatter`() {
        val epics = DepotDeSpecifications(racineComplete()).charger().epics
        assertEquals(1, epics.size)
        val epic = epics.first()
        assertEquals("epic-1", epic.id)
        assertEquals("inventaire-du-stock", epic.slug)
        assertEquals(listOf("OBJ", "CS-1"), epic.sourcesPrd)
    }

    @Test
    fun `charge une feature avec son dossier parent`() {
        val features = DepotDeSpecifications(racineComplete()).charger().features
        assertEquals(1, features.size)
        val feature = features.first()
        assertEquals("feat-1.1", feature.id)
        assertEquals("epic-1", feature.epic)
        assertEquals("inventaire-du-stock", feature.dossier)
    }

    @Test
    fun `un depot sans artefacts renvoie tout vide`() {
        val racine = createTempDirectory()
        (racine / "chemins.properties").writeText(
            "PRD=produit/PRD.md\nTABLE_DERIVATION=specs/table.md\nEPICS=specs/epics\nFEATURES=specs/features\n",
        )
        val specs = DepotDeSpecifications(racine).charger()
        assertEquals(null, specs.prd)
        assertEquals(null, specs.table)
        assertTrue(specs.epics.isEmpty())
        assertTrue(specs.features.isEmpty())
    }
}
