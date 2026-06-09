package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/** Tout source_prd existe dans le PRD ; tout épic parent existe et est cohérent. */
class RegleDeReference : Regle {

    override fun verifier(specs: Specifications): List<Violation> {
        val v = mutableListOf<Violation>()
        val codesPrd = specs.prd?.codes() ?: emptySet()
        val idsEpics = specs.epics.mapNotNull { it.id }.toSet() +
            (specs.table?.identifiants() ?: emptySet())

        for (epic in specs.epics) {
            for (src in epic.sourcesPrd) {
                if (src !in codesPrd) v += violation("${epic.chemin}: source_prd « $src » absent du PRD")
            }
        }
        for (feature in specs.features) {
            for (src in feature.sourcesPrd) {
                if (src !in codesPrd) v += violation("${feature.chemin}: source_prd « $src » absent du PRD")
            }
            val parent = feature.epic
            if (parent != null && parent !in idsEpics) {
                v += violation("${feature.chemin}: epic parent « $parent » inexistant")
            }
            val numeroEpic = feature.numeros?.first
            if (numeroEpic != null && parent != "epic-$numeroEpic") {
                v += violation("${feature.chemin}: id « ${feature.id} » incohérent avec epic « $parent » (attendu epic-$numeroEpic)")
            }
        }
        return v
    }

    private fun violation(message: String) = Violation(CategorieViolation.REFERENCE, message)
}
