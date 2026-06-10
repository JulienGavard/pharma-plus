package pharmaplus.harnais.domaine

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Invariants transverses portés par l'agrégat (adr-004). */
class SpecificationsTest {

    private fun violations(specs: Specifications) = specs.verifierConformite().violations

    // --- Couverture ---

    @Test
    fun `un element couvrable non couvert produit deux violations`() {
        val v = violations(specs(prd = prd(IdentifiantPrd("CS-9"))))
            .filter { it.categorie == CategorieViolation.COUVERTURE }
        assertEquals(2, v.size)
        assertTrue(v.all { it.message.startsWith("CS-9") })
    }

    @Test
    fun `un element couvert par epic et feature ne produit aucune violation de couverture`() {
        val v = violations(
            specs(
                prd = prd(IdentifiantPrd("OBJ")),
                epics = listOf(epic(sourcesPrd = listOf("OBJ"))),
                features = listOf(feature(sourcesPrd = listOf("OBJ"))),
            ),
        )
        assertTrue(v.none { it.categorie == CategorieViolation.COUVERTURE })
    }

    @Test
    fun `un element exempte n'est pas soumis a couverture`() {
        val v = violations(specs(prd = prd(IdentifiantPrd("CL-4", exempteeDeCouverture = true))))
        assertTrue(v.none { it.categorie == CategorieViolation.COUVERTURE })
    }

    // --- Dérivation ---

    @Test
    fun `epic dans la table mais sans fichier`() {
        val v = violations(
            specs(table = TableDeDerivation(mapOf(1 to "epic-1", 2 to "epic-2")), epics = listOf(epic(id = "epic-1"))),
        )
        assertTrue(v.any { it.message.contains("epic-2") && it.message.contains("sans fichier") })
    }

    @Test
    fun `epic en fichier absent de la table`() {
        val v = violations(
            specs(
                table = TableDeDerivation(mapOf(1 to "epic-1")),
                epics = listOf(epic(id = "epic-1"), epic(id = "epic-5", slug = "autre", titre = "Autre")),
            ),
        )
        assertTrue(v.any { it.message.contains("epic-5") && it.message.contains("absent de la table") })
    }

    // --- Unicité ---

    @Test
    fun `un id d'epic duplique est signale`() {
        val v = violations(
            specs(
                epics = listOf(
                    epic(id = "epic-1", slug = "a", nomFichier = "a", titre = "A"),
                    epic(id = "epic-1", slug = "b", nomFichier = "b", titre = "B"),
                ),
            ),
        )
        assertTrue(v.any { it.message.contains("id épic dupliqué") })
    }

    // --- Parenté ---

    @Test
    fun `l'epic parent d'une feature doit exister`() {
        val v = violations(specs(features = listOf(feature(id = "feat-9.1", epic = "epic-9"))))
        assertTrue(v.any { it.message.contains("epic parent") })
    }

    // --- Dossier ---

    @Test
    fun `le dossier d'une feature doit valoir le slug de son epic parent`() {
        val v = violations(
            specs(
                epics = listOf(epic(id = "epic-1", slug = "inventaire-du-stock")),
                features = listOf(feature(epic = "epic-1", dossier = "mauvais-dossier")),
            ),
        )
        assertTrue(v.any { it.message.contains("dossier") })
    }
}
