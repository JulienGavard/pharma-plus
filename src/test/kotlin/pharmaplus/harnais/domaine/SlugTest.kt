package pharmaplus.harnais.domaine

import kotlin.test.Test
import kotlin.test.assertEquals

class SlugTest {

    @Test
    fun `slug des exemples de reference (gdr-l2-006)`() {
        assertEquals("inventaire-du-stock", Slug.depuisTitre("Inventaire du stock").valeur)
        assertEquals(
            "suivi-des-economies-et-reste-a-charge",
            Slug.depuisTitre("Suivi des économies & reste à charge").valeur,
        )
        assertEquals(
            "conformite-et-donnees-personnelles",
            Slug.depuisTitre("Conformité & données personnelles").valeur,
        )
    }

    @Test
    fun `accents et apostrophes`() {
        assertEquals("mise-a-jour-consommation", Slug.depuisTitre("Mise à jour consommation").valeur)
        assertEquals("droit-a-l-effacement", Slug.depuisTitre("Droit à l'effacement").valeur)
    }

    @Test
    fun `le slug est idempotent`() {
        val une = Slug.depuisTitre("Scan d'une boîte par code-barres").valeur
        assertEquals(une, Slug.depuisTitre(une).valeur)
    }
}
