# ADR-L2-007 — Frontmatter YAML des épics

**Date** : 2026-06-09
**Statut** : Accepté
**Complète** : ADR-L2-000 (template épic) en ajoutant une frontmatter machine-lisible

## Contexte

Le template d'épic (ADR-L2-000) décrit les champs en prose à clés grasses (`**Valeur** : …`). Cette forme n'est pas parsable de façon fiable par un harnais de tests. Pour rendre les épics testables, leurs métadonnées doivent vivre dans une frontmatter YAML structurée, la prose restant réservée au corps descriptif.

## Décision

Chaque fichier épic commence par une frontmatter YAML délimitée par `---`, suivie du corps en prose.

### Schéma de la frontmatter

```yaml
---
id: epic-N                 # entier N = numéro d'épic (ADR-L2-005)
slug: <slugify(titre)>     # ADR-L2-006
titre: <libellé canonique court>
priorite: critique | haute | moyenne | basse
lot: <entier>
source_prd: [<ID PRD>, ...]   # ADR-L1-006 ; liste non vide
---
```

### Règles

- Tous les champs sont obligatoires ; `source_prd` est une liste non vide.
- `id` vaut `epic-N` où N est le numéro stable défini dans la table de dérivation.
- `slug` est égal à `slugify(titre)` et égal au nom de fichier (sans extension).
- `priorite` appartient à l'énumération en minuscules sans accent : `critique`, `haute`, `moyenne`, `basse`.
- Le corps conserve les sections descriptives (Valeur, Scope) du template ADR-L2-000.

## Conséquences

- Le harnais vérifie : présence et non-vacuité des champs, valeur d'énumération valide, `slug == slugify(titre) == nom de fichier`, et `source_prd` ne référençant que des ID existant dans le PRD
- Les fichiers épics existants sont réécrits pour porter la frontmatter (contenu descriptif conservé)
