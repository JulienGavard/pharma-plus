# Table de dérivation — PRD → Épics

**Générée le** : 2026-06-09
**Source** : conception/registres/produit/PRD.md + conception/registres/produit/pdr-001

Les références utilisent les identifiants stables du PRD (gdr-l1-006).

| Élément PRD | Épic(s) dérivé(s) |
|---|---|
| `OBJ` (visibilité permanente sur le stock) | epic-1 Inventaire du stock |
| `OBJ` (éviter achats inutiles, gaspillage, prises périmées) | epic-1 + epic-2 |
| `OBJ` / `CS-5` (estimer les économies sur le reste à charge — pdr-001) | epic-3 Suivi des économies & reste à charge |
| `CS-1` (< 10 s pour vérifier possession et validité) | epic-1 |
| `CS-2` (ne rachète plus ce qu'il possède déjà) | epic-1 |
| `CS-3` (alerte avant péremption) | epic-2 |
| `CS-4` (< 30 s pour ajouter ou consommer) | epic-1 |
| `CL-1` `CL-2` `CL-3` (RGPD, HDS, offline-first) | epic-4 Conformité & données personnelles |
| `CL-4` (pas de recommandation médicale) | Transversale — interdiction, aucun épic dédié (gdr-l1-006) |
| `CT-1` (scan offline) | epic-1 + epic-4 |
| `CT-2` (prix et taux sécu via CIP/ANSM) | epic-1 + epic-3 |
| `CR-1` `CR-2` (saisie taux mutuelle + calcul auto) | epic-3 |
| `CB-1` (zéro friction à l'ajout) | epic-1 |

## Épics produits

Le numéro d'épic est stable et fait autorité (voir gdr-l2-005) : il détermine le préfixe `N` des features (`N.M`) et ne change jamais une fois attribué.

| N° | id | Épic | Priorité | Lot |
|---|---|---|---|---|
| 1 | epic-1 | Inventaire du stock | critique | 1 |
| 2 | epic-2 | Alertes & prévention du gaspillage | haute | 1 |
| 3 | epic-3 | Suivi des économies & reste à charge | haute | 1 |
| 4 | epic-4 | Conformité & données personnelles | critique | 1 |
