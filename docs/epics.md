# Épics — Pharma Plus

## Epic 1 — Gestion du stock
**Valeur** : L'utilisateur sait en temps réel ce qu'il a chez lui, en quelle quantité et jusqu'à quand
**Scope** : Ajout, consultation, mise à jour et suppression des médicaments en stock. Exclut les alertes (Epic 2) et la résolution des données médicament (Epic 3)
**Priorité** : Critique
**Lot** : Lot 1

---

## Epic 2 — Alertes de péremption
**Valeur** : L'utilisateur est prévenu avant qu'un médicament ne périsse, sans avoir à surveiller lui-même
**Scope** : Notifications push et indicateurs visuels pour les médicaments proches de la date de péremption. Exclut les alertes de stock faible
**Priorité** : Haute
**Lot** : Lot 1

---

## Epic 3 — Intégration catalogue CIP/ANSM
**Valeur** : L'ajout d'un médicament est instantané — un scan suffit, le reste est automatique
**Scope** : Résolution du médicament depuis le code-barres CIP, récupération des informations produit (nom, forme, dosage), du prix public et du taux de remboursement Sécurité Sociale. Fallback saisie manuelle si le médicament est absent de la base
**Priorité** : Critique
**Lot** : Lot 1

---

## Epic 4 — Estimation des économies
**Valeur** : L'utilisateur voit concrètement combien il a économisé sur son reste à charge grâce à l'application
**Scope** : Calcul du reste à charge évité par médicament et cumul sur la durée. En Lot 1, le taux mutuelle est saisi manuellement. En Lot 2, il est récupéré automatiquement
**Priorité** : Haute
**Lot** : Lot 1 / Lot 2

---

## Epic 5 — Conformité et données personnelles
**Valeur** : L'utilisateur garde le contrôle total sur ses données de santé
**Scope** : Consentement RGPD, stockage local offline-first, export et suppression des données. Exclut toute synchronisation cloud en Lot 1
**Priorité** : Critique
**Lot** : Lot 1
