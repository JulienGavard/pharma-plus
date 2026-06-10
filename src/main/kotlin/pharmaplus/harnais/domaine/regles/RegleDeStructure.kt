package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/** Délègue la validation structurelle à chaque entité (adr-003). */
class RegleDeStructure : Regle {

    override fun verifier(specs: Specifications): List<Violation> =
        specs.epics.flatMap { it.violationsStructurelles() } +
            specs.features.flatMap { it.violationsStructurelles() }
}
