package pharmaplus.harnais.domaine.regles

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.Violation

/** Correspondance exacte entre la table de dérivation et les fichiers d'épics (gdr-l2-001). */
class RegleDeDerivation : Regle {

    override fun verifier(specs: Specifications): List<Violation> {
        val table = specs.table ?: return emptyList()
        val v = mutableListOf<Violation>()

        val idsTable = table.identifiants()
        val idsFichiers = specs.epics.mapNotNull { it.id }.toSet()

        for (id in (idsTable - idsFichiers).sorted()) {
            v += violation("épic « $id » présent dans la table mais sans fichier")
        }
        for (id in (idsFichiers - idsTable).sorted()) {
            v += violation("épic « $id » présent en fichier mais absent de la table")
        }
        for ((numero, id) in table.epics) {
            if (id != "epic-$numero") v += violation("table: numéro $numero incohérent avec id « $id »")
        }
        return v
    }

    private fun violation(message: String) = Violation(CategorieViolation.DERIVATION, message)
}
