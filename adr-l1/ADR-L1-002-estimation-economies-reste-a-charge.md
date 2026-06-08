# ADR-L1-002 — Modification PRD : estimation des économies sur le reste à charge

**Date** : 2026-06-08
**Statut** : Accepté

## Section modifiée
Objectif du produit, Critères de succès, Contraintes

## Avant
Le produit se concentrait uniquement sur la visibilité du stock et la prévention du gaspillage. Aucune dimension financière n'était adressée.

## Après
Le produit est capable d'estimer les économies réalisées grâce à l'optimisation du stock, en calculant le reste à charge évité (prix médicament − remboursement sécu − remboursement mutuelle).

L'utilisateur renseigne son taux de remboursement mutuelle une fois dans ses paramètres. Le taux sécu et le prix sont récupérés automatiquement depuis la base publique des médicaments (CIP/ANSM, déjà prévue dans les contraintes techniques).

## Raison du changement
À chaque passage en pharmacie, l'utilisateur peut payer un reste à charge. En évitant d'acheter des médicaments déjà en stock, il réalise des économies mesurables. Cette dimension financière renforce la valeur perçue du produit et constitue un argument d'adoption fort.

## Impact
- Nouveau critère de succès : l'utilisateur peut consulter les économies estimées générées par l'application
- Nouvelle contrainte : l'utilisateur doit pouvoir saisir son taux de remboursement mutuelle
- La base CIP/ANSM (déjà prévue) devra aussi exposer les données de prix et taux sécu
