# Feature 1.1 — Scan d'une boîte par code-barres

**Description** : L'utilisateur scanne le code-barres d'une boîte de médicament. L'application récupère automatiquement le nom, la forme, le dosage et la date de péremption depuis la base CIP/ANSM, puis ajoute la boîte au stock.
**Critère d'acceptation** : Après scan d'un code-barres valide, la boîte apparaît dans le stock avec ses informations complètes en moins de 5 secondes. Le scan fonctionne hors connexion.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Critère de succès 4 (< 30 secondes pour ajouter), Contrainte technique (scan offline, base CIP/ANSM), Contrainte business (zéro friction)
