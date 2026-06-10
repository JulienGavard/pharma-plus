package pharmaplus.harnais.application

import kotlin.io.path.Path
import kotlin.io.path.absolute
import kotlin.system.exitProcess

/**
 * Point d'entrée du harnais. Valide les artefacts du dépôt et sort avec le code 0
 * (conforme) ou 1 (violations). La racine peut être passée en argument ;
 * par défaut, le répertoire courant.
 */
fun main(args: Array<String>) {
    val racine = Path(args.firstOrNull() ?: "").absolute()
    exitProcess(Harnais(racine).executer())
}
