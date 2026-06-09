# GDR-L3-002 — Frontmatter YAML des features

**Date** : 2026-06-09
**Statut** : Accepté
**Complète** : ADR-L3-000 (template feature) en ajoutant une frontmatter machine-lisible

## Contexte

Comme pour les épics (ADR-L2-007), les métadonnées des features doivent être structurées en frontmatter YAML pour être vérifiables par le harnais de tests. La prose reste réservée au corps (Description, Critère d'acceptation).

## Décision

Chaque fichier feature commence par une frontmatter YAML délimitée par `---`, suivie du corps en prose.

### Schéma de la frontmatter

```yaml
---
id: feat-N.M               # N = numéro d'épic parent, M = index de la feature
epic: epic-N               # référence à l'épic parent
slug: <slugify(titre)>     # ADR-L2-006
titre: <libellé canonique court>
priorite: critique | haute | moyenne | basse
lot: <entier>
source_prd: [<ID PRD>, ...]   # ADR-L1-006 ; liste non vide
---
```

### Règles

- Tous les champs sont obligatoires ; `source_prd` est une liste non vide.
- `id` vaut `feat-N.M` ; `N` est cohérent avec le champ `epic` (`epic-N`) et avec le numéro d'épic de la table de dérivation.
- Le nom de fichier est `N-M-<slug>.md` dans le dossier `docs/features/<slug-de-l-epic>/`.
- `slug` est égal à `slugify(titre)`.
- `priorite` appartient à l'énumération `critique`, `haute`, `moyenne`, `basse`.
- Le corps conserve les sections descriptives (Description, Critère d'acceptation) du template ADR-L3-000 et n'exprime jamais de recommandation médicale ou posologique.

## Conséquences

- Le harnais vérifie : champs présents/non vides, énumération valide, cohérence `id`↔`epic`, `epic` pointant vers un épic existant, `slug == slugify(titre)`, nom de fichier `N-M-slug.md`, et `source_prd` ne référençant que des ID PRD existants
- Les fichiers features existants sont réécrits pour porter la frontmatter (contenu descriptif conservé)
