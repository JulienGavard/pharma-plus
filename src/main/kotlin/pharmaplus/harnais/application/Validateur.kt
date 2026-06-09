package pharmaplus.harnais.application

import pharmaplus.harnais.domaine.RapportDeConformite
import pharmaplus.harnais.domaine.regles.Regle
import pharmaplus.harnais.domaine.regles.RegleDUnicite
import pharmaplus.harnais.domaine.regles.RegleDeCouverture
import pharmaplus.harnais.domaine.regles.RegleDeDerivation
import pharmaplus.harnais.domaine.regles.RegleDeNommage
import pharmaplus.harnais.domaine.regles.RegleDeReference
import pharmaplus.harnais.domaine.regles.RegleDeStructure
import pharmaplus.harnais.infrastructure.DepotDeSpecifications
import pharmaplus.harnais.infrastructure.RapportConsole
import java.nio.file.Path

/** Orchestre la validation : charge les artefacts, applique les règles, produit le rapport. */
class Validateur(
    private val racine: Path,
    private val regles: List<Regle> = REGLES_PAR_DEFAUT,
    private val rapportConsole: RapportConsole = RapportConsole(),
) {
    /** Retourne le code de sortie : 0 si conforme, 1 sinon. */
    fun valider(): Int {
        val specs = DepotDeSpecifications(racine).charger()
        val violations = regles.flatMap { it.verifier(specs) }
        val rapport = RapportDeConformite(violations)
        rapportConsole.afficher(specs, rapport)
        return if (rapport.estConforme) 0 else 1
    }

    private companion object {
        val REGLES_PAR_DEFAUT: List<Regle> = listOf(
            RegleDeStructure(),
            RegleDeReference(),
            RegleDeCouverture(),
            RegleDeDerivation(),
            RegleDeNommage(),
            RegleDUnicite(),
        )
    }
}
