package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/** Chaque élément couvrable du PRD est porté par au moins un épic ET une feature (gdr-l1-006). */
class RegleDeCouverture : Regle {

    override fun verifier(specs: Specifications): List<Violation> {
        val couvrables = specs.prd?.codesCouvrables() ?: return emptyList()
        val parEpics = specs.epics.flatMap { it.sourcesPrd }.toSet()
        val parFeatures = specs.features.flatMap { it.sourcesPrd }.toSet()

        val v = mutableListOf<Violation>()
        for (code in couvrables.sorted()) {
            if (code !in parEpics) v += violation("$code: couvert par aucun épic")
            if (code !in parFeatures) v += violation("$code: couvert par aucune feature")
        }
        return v
    }

    private fun violation(message: String) = Violation(CategorieViolation.COUVERTURE, message)
}
