package pharmaplus.harnais.infrastructure

import pharmaplus.harnais.domaine.CategorieViolation
import pharmaplus.harnais.domaine.RapportDeConformite
import pharmaplus.harnais.domaine.Specifications
import java.io.PrintStream

/** Logique technique : rend un rapport de conformité sur la sortie standard (UTF-8). */
class RapportConsole(flux: PrintStream = PrintStream(System.out, true, Charsets.UTF_8)) {

    private val sortie = flux

    fun afficher(specs: Specifications, rapport: RapportDeConformite) {
        val totalPrd = specs.prd?.identifiants?.size ?: 0
        val couvrables = specs.prd?.codesCouvrables()?.size ?: 0

        sortie.println("Pharma Plus — validation du harnais")
        sortie.println("  PRD     : $totalPrd identifiants ($couvrables couvrables)")
        sortie.println(
            "  Épics   : ${specs.epics.size} fichier(s) | " +
                "Features : ${specs.features.size} | " +
                "Table : ${specs.table?.epics?.size ?: 0} épic(s)"
        )
        sortie.println()

        if (rapport.estConforme) {
            sortie.println("[OK] Conforme — aucune violation.")
            return
        }

        val parCategorie = rapport.parCategorie()
        for (categorie in CategorieViolation.entries) {
            val violations = parCategorie[categorie] ?: continue
            sortie.println("[ECHEC] ${categorie.libelle} (${violations.size}) :")
            for (violation in violations) sortie.println("   - ${violation.message}")
            sortie.println()
        }
        sortie.println("Total : ${rapport.total} violation(s).")
    }
}
