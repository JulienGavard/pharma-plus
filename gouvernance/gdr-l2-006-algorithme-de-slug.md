# GDR-L2-006 — Algorithme de slug (fonction pure)

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Les noms de fichiers des épics (`docs/epics/[slug].md`) et des features (`docs/features/[epic-slug]/[N-M]-[slug].md`) dépendent d'un « slug » dérivé du titre. Jusqu'ici la règle était approximative (« minuscules, espaces → tirets, accents supprimés »), incomplète (le cas de `&` n'était pas spécifié) et n'imposait pas que le slug soit une fonction du titre. Un slug choisi librement à la génération rend les noms de fichiers non reproductibles — contraire à l'objectif de déterminisme.

## Décision

Le slug est défini comme une **fonction pure** `slugify(titre)`, canonique et partagée par les niveaux L2 (épics) et L3 (features). Le slug d'un artefact **doit** être égal à `slugify(titre)` ; il n'est jamais choisi librement.

### Définition de `slugify(s)`

1. Convertir `s` en minuscules.
2. Remplacer `&` par ` et ` (espace-et-espace).
3. Normaliser en Unicode NFD et supprimer les marques diacritiques combinantes (é→e, à→a, ç→c, î→i, …).
4. Remplacer toute suite maximale de caractères hors `[a-z0-9]` par un unique tiret `-`.
5. Supprimer les tirets de début et de fin.

Le résultat n'utilise que `[a-z0-9-]`, sans tiret en début/fin ni tiret double. La fonction est idempotente : `slugify(slugify(s)) == slugify(s)`.

### Exemples de référence

| Titre | Slug |
|---|---|
| Inventaire du stock | `inventaire-du-stock` |
| Suivi des économies & reste à charge | `suivi-des-economies-et-reste-a-charge` |
| Conformité & données personnelles | `conformite-et-donnees-personnelles` |

## Conséquences

- Le harnais de tests peut vérifier `slug == slugify(titre)` et `nom de fichier dérivé du slug`
- Le `titre` doit être un libellé canonique court, puisqu'il détermine le nom de fichier
- Cette fonction est l'unique source de vérité du slug ; ADR-L2-007 et ADR-L3-002 y renvoient sans la redéfinir
