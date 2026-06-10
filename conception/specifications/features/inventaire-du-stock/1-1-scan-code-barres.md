---
id: feat-1.1
epic: epic-1
slug: scan-code-barres
titre: Scan code-barres
priorite: critique
lot: 1
source_prd: [CS-4, CT-1, CT-2, CB-1]
---

# Feature 1.1 — Scan code-barres

**Description** : L'utilisateur scanne le code-barres d'une boîte. L'application récupère automatiquement le nom, la forme, le dosage et la date de péremption depuis la base CIP/ANSM, puis ajoute la boîte au stock.
**Critère d'acceptation** : Après scan d'un code-barres valide, la boîte apparaît dans le stock avec ses informations en moins de 5 secondes. Le scan fonctionne hors connexion.
