package pharmaplus.harnais.infrastructure

/**
 * Frontmatter parsée, avec **accès typé** : évite les casts non vérifiés chez l'appelant.
 */
class Frontmatter(private val champs: Map<String, Any>) {

    fun chaine(cle: String): String? = champs[cle] as? String

    fun liste(cle: String): List<String> = (champs[cle] as? List<*>)?.filterIsInstance<String>() ?: emptyList()
}
