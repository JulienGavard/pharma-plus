package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.Epic
import pharmaplus.harnais.domaine.Feature
import pharmaplus.harnais.domaine.Priorite
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/** Frontmatter présente, champs obligatoires non vides, énumérations valides (gdr-l2-007, gdr-l3-002). */
class RegleDeStructure : Regle {

    override fun verifier(specs: Specifications): List<Violation> =
        specs.epics.flatMap { verifierEpic(it) } + specs.features.flatMap { verifierFeature(it) }

    private fun verifierEpic(epic: Epic): List<Violation> {
        if (!epic.frontmatterPresente) return listOf(violation("${epic.chemin}: frontmatter absente"))
        val v = mutableListOf<Violation>()
        if (epic.id.isNullOrBlank()) v += violation("${epic.chemin}: champ « id » manquant/vide")
        if (epic.slug.isNullOrBlank()) v += violation("${epic.chemin}: champ « slug » manquant/vide")
        if (epic.titre.isNullOrBlank()) v += violation("${epic.chemin}: champ « titre » manquant/vide")
        if (epic.priorite.isNullOrBlank()) v += violation("${epic.chemin}: champ « priorite » manquant/vide")
        if (epic.lot.isNullOrBlank()) v += violation("${epic.chemin}: champ « lot » manquant/vide")
        if (epic.sourcesPrd.isEmpty()) v += violation("${epic.chemin}: champ « source_prd » manquant/vide")
        if (epic.priorite != null && !Priorite.estValide(epic.priorite))
            v += violation("${epic.chemin}: priorite invalide « ${epic.priorite} »")
        if (!epic.lot.isNullOrBlank() && epic.lot.toIntOrNull() == null)
            v += violation("${epic.chemin}: lot non entier « ${epic.lot} »")
        if (epic.id != null && epic.numero == null)
            v += violation("${epic.chemin}: id épic invalide « ${epic.id} » (attendu epic-N)")
        return v
    }

    private fun verifierFeature(feature: Feature): List<Violation> {
        if (!feature.frontmatterPresente) return listOf(violation("${feature.chemin}: frontmatter absente"))
        val v = mutableListOf<Violation>()
        if (feature.id.isNullOrBlank()) v += violation("${feature.chemin}: champ « id » manquant/vide")
        if (feature.epic.isNullOrBlank()) v += violation("${feature.chemin}: champ « epic » manquant/vide")
        if (feature.slug.isNullOrBlank()) v += violation("${feature.chemin}: champ « slug » manquant/vide")
        if (feature.titre.isNullOrBlank()) v += violation("${feature.chemin}: champ « titre » manquant/vide")
        if (feature.priorite.isNullOrBlank()) v += violation("${feature.chemin}: champ « priorite » manquant/vide")
        if (feature.lot.isNullOrBlank()) v += violation("${feature.chemin}: champ « lot » manquant/vide")
        if (feature.sourcesPrd.isEmpty()) v += violation("${feature.chemin}: champ « source_prd » manquant/vide")
        if (feature.priorite != null && !Priorite.estValide(feature.priorite))
            v += violation("${feature.chemin}: priorite invalide « ${feature.priorite} »")
        if (!feature.lot.isNullOrBlank() && feature.lot.toIntOrNull() == null)
            v += violation("${feature.chemin}: lot non entier « ${feature.lot} »")
        if (feature.id != null && feature.numeros == null)
            v += violation("${feature.chemin}: id feature invalide « ${feature.id} » (attendu feat-N.M)")
        return v
    }

    private fun violation(message: String) = Violation(CategorieViolation.STRUCTURE, message)
}
