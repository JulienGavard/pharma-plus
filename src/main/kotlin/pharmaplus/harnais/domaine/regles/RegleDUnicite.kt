package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/** Pas deux artefacts partageant le même id ou slug. */
class RegleDUnicite : Regle {

    override fun verifier(specs: Specifications): List<Violation> {
        val v = mutableListOf<Violation>()
        v += doublons(specs.epics.mapNotNull { it.id }) { "id épic dupliqué « $it »" }
        v += doublons(specs.epics.mapNotNull { it.slug }) { "slug épic dupliqué « $it »" }
        v += doublons(specs.features.mapNotNull { it.id }) { "id feature dupliqué « $it »" }
        v += doublons(
            specs.features
                .filter { it.epic != null && it.slug != null }
                .map { "${it.epic}/${it.slug}" }
        ) { "slug feature dupliqué dans l'épic « $it »" }
        return v
    }

    private fun doublons(valeurs: List<String>, message: (String) -> String): List<Violation> =
        valeurs.groupingBy { it }.eachCount()
            .filter { it.value > 1 }
            .keys
            .map { Violation(CategorieViolation.UNICITE, message(it)) }
}
