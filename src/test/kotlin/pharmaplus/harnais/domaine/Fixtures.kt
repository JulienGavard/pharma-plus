package pharmaplus.harnais.domaine

/** Constructeurs d'objets de test, avec des valeurs par défaut cohérentes. */

fun epic(
    id: String? = "epic-1",
    slug: String? = "inventaire-du-stock",
    titre: String? = "Inventaire du stock",
    priorite: String? = "critique",
    lot: String? = "1",
    sourcesPrd: List<String> = listOf("OBJ"),
    nomFichier: String = slug ?: "sans-slug",
    dossier: String = "epics",
    chemin: String = "docs/epics/$nomFichier.md",
    frontmatterPresente: Boolean = true,
) = Epic(chemin, nomFichier, frontmatterPresente, id, slug, titre, priorite, lot, sourcesPrd)

fun feature(
    id: String? = "feat-1.1",
    epic: String? = "epic-1",
    slug: String? = "scan-code-barres",
    titre: String? = "Scan code-barres",
    priorite: String? = "haute",
    lot: String? = "1",
    sourcesPrd: List<String> = listOf("CS-4"),
    dossier: String = "inventaire-du-stock",
    nomFichier: String = run {
        val prefixe = id?.removePrefix("feat-")?.replace(".", "-")
        if (prefixe != null && slug != null) "$prefixe-$slug" else (slug ?: "sans-slug")
    },
    chemin: String = "docs/features/$dossier/$nomFichier.md",
    frontmatterPresente: Boolean = true,
) = Feature(chemin, nomFichier, dossier, frontmatterPresente, id, epic, slug, titre, priorite, lot, sourcesPrd)

fun prd(vararg identifiants: IdentifiantPrd) = Prd(identifiants.toList())

fun specs(
    prd: Prd? = null,
    table: TableDeDerivation? = null,
    epics: List<Epic> = emptyList(),
    features: List<Feature> = emptyList(),
) = Specifications(prd, table, epics, features)
