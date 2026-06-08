# Features — Pharma Plus

## Epic : Inventaire du stock

### Feature 1.1 — Scan d'une boîte par code-barres
**Description** : L'utilisateur scanne le code-barres d'une boîte de médicament. L'application récupère automatiquement le nom, la forme, le dosage et la date de péremption depuis la base CIP/ANSM, puis ajoute la boîte au stock.
**Critère d'acceptation** : Après scan d'un code-barres valide, la boîte apparaît dans le stock avec ses informations complètes en moins de 5 secondes. Le scan fonctionne hors connexion.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Critère de succès 4 (< 30 secondes pour ajouter), Contrainte technique (scan offline, base CIP/ANSM), Contrainte business (zéro friction)

### Feature 1.2 — Ajout manuel d'une boîte
**Description** : L'utilisateur peut ajouter un médicament manuellement par saisie du nom, de la quantité et de la date de péremption, pour les boîtes dont le code-barres est illisible ou absent.
**Critère d'acceptation** : Un médicament ajouté manuellement apparaît dans le stock. La saisie complète prend moins de 30 secondes.
**Priorité** : Haute
**Lot** : Lot 1
**Source PRD** : Critère de succès 4 (< 30 secondes pour ajouter), Contrainte business (zéro friction)

### Feature 1.3 — Consultation du stock
**Description** : L'utilisateur voit la liste de tous ses médicaments en stock, avec pour chacun : nom, quantité restante, date de péremption, et indicateur visuel d'état (valide / expirant / périmé).
**Critère d'acceptation** : L'utilisateur peut vérifier la présence et la validité d'un médicament en moins de 10 secondes depuis l'ouverture de l'application.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Critère de succès 1 (< 10 secondes), Objectif du produit (visibilité permanente)

### Feature 1.4 — Mise à jour du stock (consommation)
**Description** : L'utilisateur déclare la consommation d'une boîte ou d'une unité, ce qui décrémente le stock automatiquement.
**Critère d'acceptation** : La mise à jour s'effectue en moins de 30 secondes. La quantité restante est reflétée immédiatement dans la liste.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Critère de succès 4 (< 30 secondes pour consommer), Objectif du produit (visibilité permanente)

### Feature 1.5 — Recherche dans le stock
**Description** : L'utilisateur peut rechercher un médicament par nom dans son stock pour vérifier rapidement sa présence avant d'aller à la pharmacie.
**Critère d'acceptation** : Les résultats apparaissent en temps réel à mesure de la saisie. Un médicament existant est identifié en moins de 10 secondes.
**Priorité** : Haute
**Lot** : Lot 1
**Source PRD** : Critère de succès 1 (< 10 secondes), Critère de succès 2 (ne rachète plus ce qu'il possède déjà)

---

## Epic : Alertes & prévention du gaspillage

### Feature 2.1 — Alerte de péremption imminente
**Description** : L'application envoie une notification à l'utilisateur lorsqu'un médicament en stock approche de sa date de péremption (à 30 jours et à 7 jours).
**Critère d'acceptation** : Une notification est envoyée dans les délais configurés avant péremption. La notification identifie clairement le médicament concerné et n'émet aucune recommandation médicale ou posologique.
**Priorité** : Haute
**Lot** : Lot 1
**Source PRD** : Critère de succès 3 (alerte avant péremption), Objectif du produit (éviter prises périmées)

---

## Epic : Suivi des économies & reste à charge

### Feature 3.1 — Configuration du profil de remboursement
**Description** : L'utilisateur saisit une fois son taux de remboursement mutuelle dans ses paramètres. Ce taux est appliqué à tous les calculs de reste à charge de l'application.
**Critère d'acceptation** : L'utilisateur peut saisir et modifier son taux mutuelle en moins de 60 secondes. Le taux est persisté localement sur l'appareil.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Contrainte remboursement (saisie taux mutuelle), ADR-L1-002

### Feature 3.2 — Calcul du reste à charge par médicament
**Description** : Pour chaque médicament en stock, l'application affiche le prix, le taux de remboursement sécu (récupéré depuis la base CIP/ANSM) et le reste à charge calculé automatiquement (prix − remboursement sécu − remboursement mutuelle).
**Critère d'acceptation** : Le reste à charge est affiché avec le détail du calcul pour chaque médicament. Les données de prix et taux sécu proviennent exclusivement de la base CIP/ANSM.
**Priorité** : Haute
**Lot** : Lot 1
**Source PRD** : Contrainte remboursement (calcul automatique), ADR-L1-002

### Feature 3.3 — Tableau de bord des économies réalisées
**Description** : L'application calcule et affiche le montant total des économies réalisées grâce à l'optimisation du stock — médicaments non rachetés car encore disponibles dans le stock, valorisés à leur reste à charge.
**Critère d'acceptation** : L'utilisateur peut consulter ses économies cumulées à tout moment. Le calcul est actualisé à chaque mise à jour du stock.
**Priorité** : Haute
**Lot** : Lot 1
**Source PRD** : Objectif du produit (estimer économies sur reste à charge), Critère de succès 5, ADR-L1-002

---

## Epic : Conformité & données personnelles

### Feature 4.1 — Stockage local offline-first
**Description** : Toutes les données de stock sont stockées localement sur l'appareil de l'utilisateur. L'application fonctionne intégralement sans connexion internet, sans transmission de données de santé à un serveur tiers.
**Critère d'acceptation** : L'application est intégralement fonctionnelle (scan, consultation, alertes) sans connexion internet. Aucune donnée de santé ne transite par un serveur externe.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Contrainte légale (évitement HDS via stockage local), Contrainte technique (scan offline)

### Feature 4.2 — Consentement RGPD et droit à l'effacement
**Description** : À la première ouverture, l'utilisateur est informé des données collectées et de leur usage, et donne son consentement explicite. Il peut supprimer l'ensemble de ses données à tout moment depuis les paramètres.
**Critère d'acceptation** : Le consentement est recueilli avant toute collecte de données. La suppression totale est effective et irréversible en moins de 5 secondes.
**Priorité** : Critique
**Lot** : Lot 1
**Source PRD** : Contrainte légale (RGPD, consentement explicite, droit à l'effacement)
