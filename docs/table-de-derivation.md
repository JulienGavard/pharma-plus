# Table de dérivation — PRD → Épics

**Générée le** : 2026-06-08
**Source** : PRD.md + ADR-L1-002

| Section PRD | Épic(s) dérivé(s) |
|---|---|
| Objectif : visibilité permanente sur le stock | Inventaire du stock |
| Objectif : éviter achats inutiles, gaspillage, prises périmées | Inventaire du stock + Alertes & prévention du gaspillage |
| Objectif : estimer les économies sur le reste à charge (ADR-L1-002) | Suivi des économies & reste à charge |
| Critère : < 10 secondes pour vérifier possession et validité | Inventaire du stock |
| Critère : ne rachète plus ce qu'il possède déjà | Inventaire du stock |
| Critère : alerte avant péremption | Alertes & prévention du gaspillage |
| Critère : < 30 secondes pour ajouter ou consommer | Inventaire du stock |
| Critère : consulter les économies estimées | Suivi des économies & reste à charge |
| Contrainte légale : RGPD, HDS, consentement, effacement | Conformité & données personnelles |
| Contrainte légale : pas de recommandation médicale | Transversale — aucun épic dédié |
| Contrainte technique : scan offline + base CIP/ANSM | Inventaire du stock |
| Contrainte technique : prix et taux sécu via CIP/ANSM | Suivi des économies & reste à charge |
| Contrainte remboursement : saisie taux mutuelle + calcul auto | Suivi des économies & reste à charge |
| Contrainte business : zéro friction à l'ajout | Inventaire du stock |

## Épics produits

| Épic | Priorité | Lot |
|---|---|---|
| Inventaire du stock | Critique | Lot 1 |
| Alertes & prévention du gaspillage | Haute | Lot 1 |
| Suivi des économies & reste à charge | Haute | Lot 1 |
| Conformité & données personnelles | Critique | Lot 1 |
