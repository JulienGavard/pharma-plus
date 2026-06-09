package pharmaplus.harnais.domaine

/** Value object : une référence stable vers un élément du PRD (gdr-l1-006). */
data class IdentifiantPrd(
    val code: String,
    val exempteeDeCouverture: Boolean = false,
) {
    val prefixe: String get() = code.substringBefore("-")

    /** Couvrable sauf si c'est une interdiction transversale (exemptée). */
    val estCouvrable: Boolean
        get() = prefixe in PREFIXES_COUVRABLES && !exempteeDeCouverture

    override fun toString(): String = code

    companion object {
        /** Préfixes dont les éléments doivent être couverts par un épic et une feature. */
        val PREFIXES_COUVRABLES = setOf("OBJ", "CS", "CL", "CT", "CR", "CB")
    }
}
