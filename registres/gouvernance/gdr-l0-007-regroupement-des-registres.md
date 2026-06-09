# GDR-L0-007 — Regroupement des registres et source unique des chemins

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

La racine du dépôt mélangeait les trois registres de décision (`gouvernance/`, `produit/`, `architecture/`) avec les documents, le code et le projet Gradle. Cela devenait illisible. Par ailleurs, les chemins de ces dossiers étaient répétés en dur dans les skills et le harnais — une duplication qui rend tout déplacement coûteux.

## Décision

### 1. Regroupement sous `registres/`

Les trois registres de décision sont déplacés sous un dossier parent **`registres/`** :

| Avant | Après |
|-------|-------|
| `gouvernance/` | `registres/gouvernance/` |
| `produit/` | `registres/produit/` |
| `architecture/` | `registres/architecture/` |

Ce regroupement précise l'emplacement défini par `gdr-l0-004` (la taxonomie elle-même — nature des registres — est inchangée). Conformément à l'assouplissement de l'immuabilité aux références croisées (`gdr-l0-004`), les chemins cités dans les records existants peuvent être mis à jour ; les citations historiques non encore alignées restent valides via l'équivalence `X/ ≡ registres/X/`.

`docs/`, `src/` et les fichiers Gradle restent à la racine (Gradle l'exige).

### 2. Source unique des chemins : `chemins.properties`

Pour ne plus répéter les chemins en dur, un fichier **`chemins.properties`** (racine) devient la **source unique de vérité** des chemins du dépôt (format `CLE=chemin`).

- Les **skills** y réfèrent via des alias (`$GOUVERNANCE`, `$PRD`, `$EPICS`…) qu'ils résolvent en lisant ce fichier.
- Le **harnais Kotlin** le lit via `infrastructure/Chemins.kt`.
- En cas de futur déplacement, **seul `chemins.properties` est modifié** (plus les éventuels records, par cross-référence).

Les **documents lisibles** (carte de gouvernance, table de dérivation) gardent les chemins **résolus** (réels), pas les alias.

## Conséquences

- `registres/` créé ; les trois registres y sont déplacés (historique git préservé).
- `chemins.properties` créé ; skills product-manager et gouverneur migrés vers les alias ; `DepotDeSpecifications` lit les chemins via `Chemins`.
- `docs/gouvernance.md` et `docs/table-de-derivation.md` mis à jour avec les chemins résolus.
- Le harnais reste vert (mêmes 32 violations à vide qu'avant le déplacement).
