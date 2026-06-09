package pharmaplus.harnais.domaine

import java.text.Normalizer

/** Value object : le slug d'un artefact, fonction pure de son titre (gdr-l2-006). */
@JvmInline
value class Slug(val valeur: String) {

    override fun toString(): String = valeur

    companion object {
        /** Applique l'algorithme de slug canonique (gdr-l2-006). */
        fun depuisTitre(titre: String): Slug {
            var texte = titre.lowercase().replace("&", " et ")
            texte = Normalizer.normalize(texte, Normalizer.Form.NFD)
                .replace(Regex("\\p{Mn}+"), "")
            texte = texte.replace(Regex("[^a-z0-9]+"), "-").trim('-')
            return Slug(texte)
        }
    }
}
