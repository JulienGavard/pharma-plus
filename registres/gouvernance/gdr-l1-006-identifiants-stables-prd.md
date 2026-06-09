# GDR-L1-006 — Identifiants stables des éléments du PRD

**Date** : 2026-06-09
**Statut** : Accepté

## Section modifiée
Structure du PRD — ajout d'identifiants sur les éléments référençables.

## Avant
Les épics et features référençaient le PRD en langage naturel et de façon positionnelle (« Critère de succès 4 »). Une référence positionnelle casse dès que le PRD est réordonné, et une référence en prose n'est pas vérifiable par une machine. La traçabilité PRD → épic → feature ne pouvait donc pas être testée automatiquement.

## Après
Chaque élément référençable du PRD porte un identifiant stable, inscrit dans `docs/PRD.md` entre crochets en tête de l'élément. Les identifiants ne changent jamais une fois attribués ; un élément supprimé ne voit pas son ID réattribué.

### Schéma d'identifiants

| Préfixe | Élément | Exemple |
|---|---|---|
| `PROB` | Problème | `PROB` |
| `USR` | Utilisateur cible | `USR` |
| `OBJ` | Objectif du produit | `OBJ` |
| `CS-N` | Critère de succès n°N | `CS-1` … `CS-5` |
| `CL-N` | Contrainte légale n°N | `CL-1` … `CL-4` |
| `CT-N` | Contrainte technique n°N | `CT-1`, `CT-2` |
| `CR-N` | Contrainte remboursement n°N | `CR-1`, `CR-2` |
| `CB-N` | Contrainte business n°N | `CB-1` |

### Distinction couverture / interdiction

Un élément qui formule une **interdiction** (ex. CL-4 « aucune recommandation médicale ») est une contrainte transversale : il n'est couvert par aucun épic dédié et est exclu de l'obligation de couverture. Tout autre élément (objectif, critère de succès, contrainte capacitaire) doit être couvert par au moins un épic et au moins une feature.

## Raison du changement
Rendre la traçabilité vérifiable par un harnais de tests. Des ID stables transforment « chaque épic cite une section du PRD » d'une consigne en prose en une assertion exécutable : tout `source_prd` cité existe ; tout élément couvrable est couvert.

## Impact
- `docs/PRD.md` est réécrit pour porter les ID (contenu inchangé, seuls les marqueurs `[ID]` sont ajoutés)
- Les frontmatter des épics (ADR-L2-007) et features (ADR-L3-002) référencent ces ID via le champ `source_prd`
- La table de dérivation référence ces ID
