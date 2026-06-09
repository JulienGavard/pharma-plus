package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.Slug
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/** slug == slugify(titre) == nom de fichier ; préfixe N-M et dossier des features (gdr-l2-006). */
class RegleDeNommage : Regle {

    override fun verifier(specs: Specifications): List<Violation> {
        val v = mutableListOf<Violation>()
        val slugParEpic: Map<String, String?> =
            specs.epics.filter { it.id != null }.associate { it.id!! to it.slug }

        for (epic in specs.epics) {
            val titre = epic.titre
            val slug = epic.slug
            if (titre != null && slug != null) {
                val attendu = Slug.depuisTitre(titre).valeur
                if (slug != attendu) v += violation("${epic.chemin}: slug « $slug » ≠ slugify(titre) « $attendu »")
            }
            if (slug != null && epic.nomFichier != slug) {
                v += violation("${epic.chemin}: nom de fichier « ${epic.nomFichier} » ≠ slug « $slug »")
            }
        }

        for (feature in specs.features) {
            val titre = feature.titre
            val slug = feature.slug
            if (titre != null && slug != null) {
                val attendu = Slug.depuisTitre(titre).valeur
                if (slug != attendu) v += violation("${feature.chemin}: slug « $slug » ≠ slugify(titre) « $attendu »")
            }
            val numeros = feature.numeros
            if (numeros != null && slug != null) {
                val attendu = "${numeros.first}-${numeros.second}-$slug"
                if (feature.nomFichier != attendu) {
                    v += violation("${feature.chemin}: nom de fichier « ${feature.nomFichier} » ≠ « $attendu »")
                }
            }
            val slugEpic = slugParEpic[feature.epic]
            if (slugEpic != null && feature.dossier != slugEpic) {
                v += violation("${feature.chemin}: dossier « ${feature.dossier} » ≠ slug de l'épic « $slugEpic »")
            }
        }
        return v
    }

    private fun violation(message: String) = Violation(CategorieViolation.NOMMAGE, message)
}
