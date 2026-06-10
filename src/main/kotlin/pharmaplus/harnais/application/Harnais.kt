package pharmaplus.harnais.application

import pharmaplus.harnais.infrastructure.DepotDeSpecifications
import pharmaplus.harnais.infrastructure.RapportConsole
import java.nio.file.Path

/**
 * Orchestre l'exécution du harnais : charge les spécifications, leur demande leur
 * conformité (l'agrégat valide lui-même), puis rend le rapport. Aucune logique métier ici.
 */
class Harnais(
    private val racine: Path,
    private val rapportConsole: RapportConsole = RapportConsole(),
) {
    /** Retourne le code de sortie : 0 si conforme, 1 sinon. */
    fun executer(): Int {
        val specs = DepotDeSpecifications(racine).charger()
        val rapport = specs.verifierConformite()
        rapportConsole.afficher(specs, rapport)
        return if (rapport.estConforme) 0 else 1
    }
}
