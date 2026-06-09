package pharmaplus.harnais.infrastructure

/**
 * Logique technique : extrait la frontmatter YAML minimale d'un contenu Markdown.
 * Gère les clés scalaires et les listes en notation « flow » (`[a, b, c]`).
 */
class LecteurDeFrontmatter {

    /** Map clé → valeur (String ou List<String>), ou null si aucune frontmatter. */
    fun lire(contenu: String): Map<String, Any>? {
        if (!contenu.startsWith("---")) return null
        val parts = contenu.split("---", limit = 3)
        if (parts.size < 3) return null

        val map = mutableMapOf<String, Any>()
        for (ligne in parts[1].lines()) {
            val l = ligne.trim()
            if (l.isEmpty() || !l.contains(":")) continue
            val cle = l.substringBefore(":").trim()
            val valeur = l.substringAfter(":").trim()
            map[cle] = if (valeur.startsWith("[") && valeur.endsWith("]")) {
                valeur.removeSurrounding("[", "]")
                    .split(",")
                    .map { it.trim().trim('\'', '"') }
                    .filter { it.isNotEmpty() }
            } else {
                valeur.trim('\'', '"')
            }
        }
        return map
    }
}
