package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.Prd
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/**
 * Délègue la référence intrinsèque aux entités (adr-003). Conserve le seul contrôle
 * inter-objets : l'épic parent d'une feature doit exister.
 */
class RegleDeReference : Regle {

    override fun verifier(specs: Specifications): List<Violation> {
        val prd = specs.prd ?: Prd(emptyList())
        val idsEpics = specs.epics.mapNotNull { it.id }.toSet() +
            (specs.table?.identifiants() ?: emptySet())

        val v = mutableListOf<Violation>()
        v += specs.epics.flatMap { it.violationsDeReference(prd) }
        v += specs.features.flatMap { it.violationsDeReference(prd) }
        for (feature in specs.features) {
            val parent = feature.epic
            if (parent != null && parent !in idsEpics) {
                v += Violation(CategorieViolation.REFERENCE, "${feature.chemin}: epic parent « $parent » inexistant")
            }
        }
        return v
    }
}
