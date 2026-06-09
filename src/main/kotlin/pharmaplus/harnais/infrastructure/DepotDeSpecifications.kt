package pharmaplus.harnais.infrastructure

import pharmaplus.harnais.domaine.Epic
import pharmaplus.harnais.domaine.Feature
import pharmaplus.harnais.domaine.IdentifiantPrd
import pharmaplus.harnais.domaine.Prd
import pharmaplus.harnais.domaine.Specifications
import pharmaplus.harnais.domaine.TableDeDerivation
import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.isDirectory
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name
import kotlin.io.path.nameWithoutExtension
import kotlin.io.path.readText

/**
 * Logique technique : reconstruit les objets métier (Prd, Epics, Features, Table)
 * à partir du système de fichiers. Seul endroit qui sait ce qu'est un fichier .md.
 * Les chemins proviennent de [Chemins] (source unique : chemins.properties).
 */
class DepotDeSpecifications(private val racine: Path) {

    private val chemins = Chemins(racine)
    private val lecteur = LecteurDeFrontmatter()

    fun charger(): Specifications = Specifications(
        prd = chargerPrd(),
        table = chargerTable(),
        epics = chargerEpics(),
        features = chargerFeatures(),
    )

    private fun chargerPrd(): Prd? {
        val fichier = chemins.prd
        if (!fichier.exists()) return null
        val regexId = Regex("""\[(PROB|USR|OBJ|CS-\d+|CL-\d+|CT-\d+|CR-\d+|CB-\d+)]""")
        val identifiants = LinkedHashMap<String, IdentifiantPrd>()
        for (ligne in fichier.readText().lines()) {
            val exempt = ligne.contains(MARQUEUR_EXEMPT)
            for (m in regexId.findAll(ligne)) {
                val code = m.groupValues[1]
                val dejaExempt = identifiants[code]?.exempteeDeCouverture ?: false
                identifiants[code] = IdentifiantPrd(code, exempt || dejaExempt)
            }
        }
        return Prd(identifiants.values.toList())
    }

    private fun chargerTable(): TableDeDerivation? {
        val fichier = chemins.tableDerivation
        if (!fichier.exists()) return null
        val regexLigne = Regex("""^\|\s*(\d+)\s*\|\s*(epic-\d+)\s*\|""")
        val epics = LinkedHashMap<Int, String>()
        for (ligne in fichier.readText().lines()) {
            val m = regexLigne.find(ligne) ?: continue
            epics[m.groupValues[1].toInt()] = m.groupValues[2]
        }
        return TableDeDerivation(epics)
    }

    private fun chargerEpics(): List<Epic> {
        val dossier = chemins.epics
        if (!dossier.isDirectory()) return emptyList()
        return dossier.listDirectoryEntries("*.md").sorted().map { fichier ->
            val fm = lecteur.lire(fichier.readText())
            Epic(
                chemin = cheminRelatif(fichier),
                nomFichier = fichier.nameWithoutExtension,
                frontmatterPresente = fm != null,
                id = fm.chaine("id"),
                slug = fm.chaine("slug"),
                titre = fm.chaine("titre"),
                priorite = fm.chaine("priorite"),
                lot = fm.chaine("lot"),
                sourcesPrd = fm.liste("source_prd"),
            )
        }
    }

    private fun chargerFeatures(): List<Feature> {
        val dossier = chemins.features
        if (!dossier.isDirectory()) return emptyList()
        val features = mutableListOf<Feature>()
        for (sousDossier in dossier.listDirectoryEntries().filter { it.isDirectory() }.sorted()) {
            for (fichier in sousDossier.listDirectoryEntries("*.md").sorted()) {
                val fm = lecteur.lire(fichier.readText())
                features += Feature(
                    chemin = cheminRelatif(fichier),
                    nomFichier = fichier.nameWithoutExtension,
                    dossier = sousDossier.name,
                    frontmatterPresente = fm != null,
                    id = fm.chaine("id"),
                    epic = fm.chaine("epic"),
                    slug = fm.chaine("slug"),
                    titre = fm.chaine("titre"),
                    priorite = fm.chaine("priorite"),
                    lot = fm.chaine("lot"),
                    sourcesPrd = fm.liste("source_prd"),
                )
            }
        }
        return features
    }

    private fun cheminRelatif(fichier: Path): String =
        racine.relativize(fichier).toString().replace('\\', '/')

    private companion object {
        const val MARQUEUR_EXEMPT = "non soumise à l'obligation de couverture"
    }
}

private fun Map<String, Any>?.chaine(cle: String): String? = this?.get(cle) as? String

@Suppress("UNCHECKED_CAST")
private fun Map<String, Any>?.liste(cle: String): List<String> =
    this?.get(cle) as? List<String> ?: emptyList()
