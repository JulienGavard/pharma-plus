package pharmaplus.harnais.infrastructure

/**
 * Logique technique : extrait la frontmatter YAML minimale d'un contenu Markdown.
 * Gère les clés scalaires et les listes en notation « flow » (`[a, b, c]`).
 */
class LecteurDeFrontmatter {

    /** Retourne la frontmatter typée, ou null si le contenu n'en comporte pas. */
    fun lire(contenu: String): Frontmatter? {
        if (!contenu.startsWith("---")) return null
        val parts = contenu.split("---", limit = 3)
        if (parts.size < 3) return null

        val champs = mutableMapOf<String, Any>()
        for (ligne in parts[1].lines()) {
            val l = ligne.trim()
            if (l.isEmpty() || !l.contains(":")) continue
            val cle = l.substringBefore(":").trim()
            val valeur = l.substringAfter(":").trim()
            champs[cle] = if (valeur.startsWith("[") && valeur.endsWith("]")) {
                valeur.removeSurrounding("[", "]")
                    .split(",")
                    .map { it.trim().trim('\'', '"') }
                    .filter { it.isNotEmpty() }
            } else {
                valeur.trim('\'', '"')
            }
        }
        return Frontmatter(champs)
    }
}
