# ADR-L2-005 — Numérotation stable des épics

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Les features portent un identifiant de la forme `N.M` (ex. `1.1`), où `N` est le numéro de l'épic parent et `M` l'index de la feature. Ce numéro d'épic conditionne le nom de fichier des features (`docs/features/[epic]/N-M-[slug].md`). Or, jusqu'ici, le numéro d'épic n'était défini nulle part : il était implicite dans l'ordre d'apparition des épics. Une réorganisation des épics pouvait donc renuméroter toutes les features de façon silencieuse — une source de non-déterminisme directement contraire à l'objectif du harnais de tests.

## Décision

**La table de dérivation (`docs/table-de-derivation.md`) est la source unique du numéro d'épic.**

Chaque épic y reçoit un numéro entier stable, attribué dans la section « Épics produits ». Ce numéro est le contrat : il détermine le préfixe `N` des features de l'épic et ne change jamais une fois attribué, même si l'ordre de présentation des épics évolue.

### Règles

- Un épic conserve son numéro à vie. Si un épic est supprimé, son numéro n'est pas réattribué à un autre épic.
- Un nouvel épic reçoit le prochain numéro disponible (max + 1), jamais un numéro libéré.
- La numérotation des features (`N.M`) dérive mécaniquement du numéro d'épic défini dans la table.

## Conséquences

- La table de dérivation, déjà désignée comme « contrat de génération » par ADR-L2-001, devient aussi la source d'autorité pour la numérotation
- Le harnais de tests pourra vérifier que tout préfixe de feature correspond à un numéro d'épic existant dans la table, et que la numérotation est continue et sans collision
- Toute modification de la table impactant les numéros entraîne une revue des noms de fichiers des features concernées
