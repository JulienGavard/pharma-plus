package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/** Une règle de gouvernance « de sortie », vérifiable sur les artefacts. */
interface Regle {
    fun verifier(specs: Specifications): List<Violation>
}
