package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/**
 * Délègue le nommage intrinsèque aux entités (adr-003). Conserve le seul contrôle
 * inter-objets : le dossier d'une feature doit correspondre au slug de son épic parent.
 */
class RegleDeNommage : Regle {

    override fun verifier(specs: Specifications): List<Violation> {
        val v = mutableListOf<Violation>()
        v += specs.epics.flatMap { it.violationsDeNommage() }
        v += specs.features.flatMap { it.violationsDeNommage() }

        val slugParEpic: Map<String, String?> =
            specs.epics.filter { it.id != null }.associate { it.id!! to it.slug }
        for (feature in specs.features) {
            val slugEpic = slugParEpic[feature.epic]
            if (slugEpic != null && feature.dossier != slugEpic) {
                v += Violation(
                    CategorieViolation.NOMMAGE,
                    "${feature.chemin}: dossier « ${feature.dossier} » ≠ slug de l'épic « $slugEpic »",
                )
            }
        }
        return v
    }
}
