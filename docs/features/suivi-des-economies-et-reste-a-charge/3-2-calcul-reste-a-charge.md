# Feature 3.2 — Calcul du reste à charge par médicament

**Description** : Pour chaque médicament en stock, l'application affiche le prix, le taux de remboursement sécu (récupéré depuis la base CIP/ANSM) et le reste à charge calculé automatiquement (prix − remboursement sécu − remboursement mutuelle).
**Critère d'acceptation** : Le reste à charge est affiché avec le détail du calcul pour chaque médicament. Les données de prix et taux sécu proviennent exclusivement de la base CIP/ANSM.
**Priorité** : Haute
**Lot** : Lot 1
**Source PRD** : Contrainte remboursement (calcul automatique), ADR-L1-002
