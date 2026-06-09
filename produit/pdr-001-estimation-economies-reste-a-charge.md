# PDR-001 — Estimation des économies sur le reste à charge

**Date** : 2026-06-08
**Statut** : Accepté

> Cette décision était initialement enregistrée en `ADR-L1-002`. Elle a été relocalisée dans le registre produit lors de la migration actée par `gdr-l0-004` (sa nature est produit, pas gouvernance). Contenu préservé.

## Décision

Le produit estime les économies réalisées grâce à l'optimisation du stock, en calculant le reste à charge évité : **prix médicament − remboursement sécu − remboursement mutuelle**.

L'utilisateur renseigne son taux de remboursement mutuelle une fois dans ses paramètres. Le taux sécu et le prix sont récupérés automatiquement depuis la base publique des médicaments (CIP/ANSM, déjà prévue dans les contraintes techniques).

## Contexte / Raison

Le produit se concentrait initialement sur la seule visibilité du stock et la prévention du gaspillage ; aucune dimension financière n'était adressée. Or, à chaque passage en pharmacie, l'utilisateur peut payer un reste à charge. En évitant d'acheter des médicaments déjà en stock, il réalise des économies mesurables. Cette dimension financière renforce la valeur perçue du produit et constitue un argument d'adoption fort.

## Impact PRD

- `OBJ` — l'objectif intègre l'estimation des économies sur le reste à charge
- `CS-5` — nouveau critère de succès : l'utilisateur peut consulter les économies estimées
- `CR-1` — nouvelle contrainte : l'utilisateur doit pouvoir saisir son taux de remboursement mutuelle
- `CR-2` — le reste à charge est calculé automatiquement
- `CT-2` — la base CIP/ANSM doit aussi exposer les données de prix et de taux sécu

## Conséquences

- Justifie l'épic « Suivi des économies & reste à charge » et ses features
- La base CIP/ANSM (déjà prévue) devient également la source des données financières
