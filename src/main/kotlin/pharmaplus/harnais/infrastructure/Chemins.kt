package pharmaplus.harnais.infrastructure

import java.nio.file.Path
import java.util.Properties
import kotlin.io.path.div
import kotlin.io.path.exists
import kotlin.io.path.inputStream

/**
 * Logique technique : résout les chemins du dépôt depuis `chemins.properties`
 * (source unique de vérité, partagée avec les skills). Des valeurs par défaut
 * couvrent l'absence du fichier.
 */
class Chemins(private val racine: Path) {

    private val proprietes = Properties().apply {
        val fichier = racine / "chemins.properties"
        if (fichier.exists()) fichier.inputStream().use { load(it) }
    }

    val prd: Path get() = resoudre("PRD", "registres/produit/PRD.md")
    val tableDerivation: Path get() = resoudre("TABLE_DERIVATION", "docs/table-de-derivation.md")
    val epics: Path get() = resoudre("EPICS", "docs/epics")
    val features: Path get() = resoudre("FEATURES", "docs/features")

    private fun resoudre(cle: String, defaut: String): Path =
        racine / proprietes.getProperty(cle, defaut)
}
