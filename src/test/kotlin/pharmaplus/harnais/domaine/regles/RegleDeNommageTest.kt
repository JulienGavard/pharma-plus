package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.epic
import pharmaplus.harnais.domaine.feature
import pharmaplus.harnais.domaine.specs
import kotlin.test.Test
import kotlin.test.assertTrue

class RegleDeNommageTest {

    private val regle = RegleDeNommage()

    @Test
    fun `delegue le nommage aux entites`() {
        val violations = regle.verifier(specs(epics = listOf(epic(slug = "inventaire-du-stock", nomFichier = "autre"))))
        assertTrue(violations.any { it.message.contains("nom de fichier") })
    }

    @Test
    fun `le dossier d'une feature doit valoir le slug de son epic parent`() {
        val violations = regle.verifier(
            specs(
                epics = listOf(epic(id = "epic-1", slug = "inventaire-du-stock")),
                features = listOf(feature(epic = "epic-1", dossier = "mauvais-dossier")),
            ),
        )
        assertTrue(violations.any { it.message.contains("dossier") })
    }
}
