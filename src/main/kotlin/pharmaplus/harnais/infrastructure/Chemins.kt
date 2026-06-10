package pharmaplus.harnais.infrastructure

import java.nio.file.Path
import java.util.Properties
import kotlin.io.path.div
import kotlin.io.path.exists
import kotlin.io.path.inputStream

/**
 * Logique technique : résout les chemins du dépôt depuis `chemins.properties`,
 * **source unique de vérité** (gdr-l0-007).
 *
 * Aucune valeur par défaut en dur : un fichier ou une clé manquante échoue
 * explicitement, plutôt que de masquer une divergence par un défaut silencieux.
 */
class Chemins(private val racine: Path) {

    private val proprietes = Properties().apply {
        val fichier = racine / FICHIER
        require(fichier.exists()) { "Fichier de chemins introuvable : $fichier" }
        fichier.inputStream().use { load(it) }
    }

    val prd: Path get() = resoudre("PRD")
    val tableDerivation: Path get() = resoudre("TABLE_DERIVATION")
    val epics: Path get() = resoudre("EPICS")
    val features: Path get() = resoudre("FEATURES")

    private fun resoudre(cle: String): Path {
        val valeur = proprietes.getProperty(cle) ?: error("Clé « $cle » absente de $FICHIER")
        return racine / valeur
    }

    private companion object {
        const val FICHIER = "chemins.properties"
    }
}
