# Table de dérivation — PRD → Épics

**Générée le** : 2026-06-08
**Source** : PRD.md + ADR-L1-002

| Section PRD | Épic(s) dérivé(s) |
|---|---|
| Objectif : visibilité permanente sur le stock | Inventaire du stock |
| Objectif : éviter achats inutiles, gaspillage, prises périmées | Inventaire du stock + Alertes & prévention du gaspillage |
| Objectif : estimer les économies sur le reste à charge (ADR-L1-002) | Suivi des économies & reste à charge |
| Critère : sait en < 10 secondes si médicament possédé et valable | Inventaire du stock |
| Critère : ne rachète plus un médicament déjà en stock | Inventaire du stock |
| Critère : reçoit une alerte avant péremption | Alertes & prévention du gaspillage |
| Critère : < 30 secondes pour ajouter ou consommer une boîte | Inventaire du stock |
| Critère : peut consulter les économies estimées | Suivi des économies & reste à charge |
| Contrainte légale : RGPD, HDS, pas de recommandation médicale | Conformité & données personnelles |
| Contrainte technique : scan code-barres offline | Inventaire du stock |
| Contrainte technique : base CIP/ANSM (prix, taux sécu) | Inventaire du stock + Suivi des économies & reste à charge |
| Contrainte remboursement : saisie taux mutuelle, calcul automatique | Suivi des économies & reste à charge |
| Contrainte business : aussi simple qu'une liste de courses | Inventaire du stock |

## Épics produits

| Épic | Priorité | Lot |
|---|---|---|
| Inventaire du stock | Critique | Lot 1 |
| Alertes & prévention du gaspillage | Haute | Lot 1 |
| Suivi des économies & reste à charge | Haute | Lot 1 |
| Conformité & données personnelles | Critique | Lot 1 |
