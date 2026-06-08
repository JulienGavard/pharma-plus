# Épics — Pharma Plus

## Inventaire du stock
**Valeur** : L'utilisateur sait à tout moment ce qu'il possède, en quelle quantité, et jusqu'à quand.
**Scope** : Inclut ajout par scan, ajout manuel, consultation du stock, mise à jour des quantités, recherche rapide. Exclut toute recommandation médicale ou posologique.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Objectif du produit (visibilité permanente), Critères de succès 1, 2, 4, Contrainte technique (scan offline, base CIP/ANSM), Contrainte business (zéro friction)

---

## Alertes & prévention du gaspillage
**Valeur** : L'utilisateur est prévenu avant qu'un médicament ne périsse, sans avoir à surveiller lui-même son stock.
**Scope** : Inclut alertes de péremption imminente. Exclut les recommandations de renouvellement d'ordonnance ou de dosage.
**Priorité** : Haute
**Lot** : Lot 1
**Source PRD** : Objectif du produit (éviter gaspillage, éviter prises périmées), Critère de succès 3

---

## Suivi des économies & reste à charge
**Valeur** : L'utilisateur visualise les économies réalisées grâce à l'optimisation de son stock, calculées sur son reste à charge réel.
**Scope** : Inclut saisie du taux mutuelle, calcul automatique du reste à charge par médicament, tableau de bord des économies cumulées. Exclut tout conseil financier ou médical.
**Priorité** : Haute
**Lot** : Lot 1
**Source PRD** : Objectif du produit (estimer économies sur reste à charge), Critère de succès 5, Contrainte remboursement, ADR-L1-002

---

## Conformité & données personnelles
**Valeur** : L'utilisateur garde le contrôle total de ses données de santé, dans le respect du RGPD et sans obligation d'hébergeur HDS.
**Scope** : Inclut stockage local offline-first, recueil du consentement éclairé, droit à l'effacement. Exclut l'hébergement cloud sans certification HDS.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Contraintes légales (RGPD, HDS, consentement explicite, droit à l'effacement), Contrainte technique (offline-first)
