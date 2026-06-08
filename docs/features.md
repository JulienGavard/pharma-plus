# Features — Pharma Plus

## Epic 1 — Gestion du stock

### Feature 1.1 — Ajout par scan code-barres
**Description** : L'utilisateur scanne le code-barres CIP d'une boîte pour l'ajouter à son stock. Le médicament est résolu automatiquement (via Epic 3). L'utilisateur confirme la quantité et la date de péremption
**Critère d'acceptation** : Un médicament est ajouté au stock en moins de 30 secondes, scan compris, sans connexion réseau
**Priorité** : Critique
**Lot** : Lot 1

### Feature 1.2 — Ajout manuel
**Description** : L'utilisateur peut ajouter un médicament sans code-barres, en saisissant le nom, la quantité et la date de péremption à la main
**Critère d'acceptation** : Le formulaire est accessible depuis la même action que le scan et permet de sauvegarder en moins de 30 secondes
**Priorité** : Haute
**Lot** : Lot 1

### Feature 1.3 — Vue liste du stock
**Description** : L'utilisateur consulte la liste complète de son stock, triée par date de péremption. Chaque entrée affiche le nom, la quantité restante et la date de péremption
**Critère d'acceptation** : L'utilisateur trouve un médicament donné et sa date de péremption en moins de 10 secondes
**Priorité** : Critique
**Lot** : Lot 1

### Feature 1.4 — Consommation d'un médicament
**Description** : L'utilisateur indique qu'il a pris ou utilisé un médicament, ce qui décrémente la quantité en stock. Si la quantité atteint zéro, le médicament est archivé
**Critère d'acceptation** : La mise à jour de la quantité est réalisable en moins de 30 secondes depuis la liste
**Priorité** : Haute
**Lot** : Lot 1

### Feature 1.5 — Suppression d'un médicament
**Description** : L'utilisateur peut retirer manuellement un médicament de son stock (jeté, perdu, donné)
**Critère d'acceptation** : La suppression est confirmée par une action intentionnelle (pas de suppression accidentelle) et irréversible après confirmation
**Priorité** : Moyenne
**Lot** : Lot 1

---

## Epic 2 — Alertes de péremption

### Feature 2.1 — Notification de péremption imminente
**Description** : L'application envoie une notification push lorsqu'un médicament approche de sa date de péremption. Le délai d'alerte est configurable (par défaut : J-30 et J-7)
**Critère d'acceptation** : L'utilisateur reçoit une notification au bon délai, même si l'application n'est pas ouverte. La notification indique le nom du médicament et la date de péremption
**Priorité** : Critique
**Lot** : Lot 1

### Feature 2.2 — Indicateur visuel dans la liste
**Description** : Les médicaments proches de péremption sont mis en évidence dans la liste (couleur ou icône) selon leur urgence
**Critère d'acceptation** : Un médicament à moins de 30 jours de péremption est immédiatement identifiable à la lecture de la liste
**Priorité** : Haute
**Lot** : Lot 1

---

## Epic 3 — Intégration catalogue CIP/ANSM

### Feature 3.1 — Résolution automatique par code CIP
**Description** : Lors du scan, le code CIP est interrogé dans la base CIP/ANSM embarquée pour récupérer le nom, la forme pharmaceutique et le dosage du médicament
**Critère d'acceptation** : Le médicament est identifié sans connexion réseau pour 100% des médicaments présents dans la base ANSM
**Priorité** : Critique
**Lot** : Lot 1

### Feature 3.2 — Récupération prix et taux de remboursement sécu
**Description** : Pour chaque médicament résolu, l'application récupère le prix public et le taux de remboursement Sécurité Sociale depuis la base CIP/ANSM
**Critère d'acceptation** : Le prix et le taux sécu sont disponibles pour tout médicament remboursable figurant dans la base
**Priorité** : Critique
**Lot** : Lot 1

### Feature 3.3 — Fallback saisie manuelle
**Description** : Si le médicament n'est pas trouvé dans la base (médicament étranger, parapharmacie), l'utilisateur peut saisir les informations manuellement
**Critère d'acceptation** : Le fallback est proposé automatiquement quand la résolution échoue, sans message d'erreur bloquant
**Priorité** : Haute
**Lot** : Lot 1

---

## Epic 4 — Estimation des économies

### Feature 4.1 — Saisie du taux mutuelle
**Description** : L'utilisateur renseigne une fois son taux de remboursement mutuelle dans ses paramètres (ex. : "ma mutuelle rembourse 80% du ticket modérateur")
**Critère d'acceptation** : Le paramètre est accessible depuis les réglages, modifiable à tout moment, et pris en compte immédiatement dans les calculs
**Priorité** : Haute
**Lot** : Lot 1

### Feature 4.2 — Calcul du reste à charge par médicament
**Description** : Pour chaque médicament en stock, l'application calcule et affiche le reste à charge réel : prix − remboursement sécu − remboursement mutuelle
**Critère d'acceptation** : Le reste à charge est affiché au niveau du détail d'un médicament, avec le détail du calcul accessible
**Priorité** : Haute
**Lot** : Lot 1

### Feature 4.3 — Tableau de bord des économies cumulées
**Description** : L'application affiche le total des économies réalisées depuis l'installation, calculé sur la base des achats évités (médicaments déjà en stock au moment d'une ordonnance)
**Critère d'acceptation** : Le tableau de bord est accessible depuis l'écran principal et affiche un montant cumulé lisible
**Priorité** : Moyenne
**Lot** : Lot 1

### Feature 4.4 — Récupération automatique du taux mutuelle
**Description** : L'utilisateur connecte son compte mutuelle pour que le taux de remboursement soit récupéré et mis à jour automatiquement
**Critère d'acceptation** : Le taux mutuelle est synchronisé sans intervention manuelle, et l'utilisateur est notifié en cas de changement de couverture
**Priorité** : Haute
**Lot** : Lot 2

---

## Epic 5 — Conformité et données personnelles

### Feature 5.1 — Consentement RGPD au premier lancement
**Description** : Au premier lancement, l'utilisateur est informé des données collectées, de leur finalité et de ses droits. Son consentement explicite est requis avant toute utilisation
**Critère d'acceptation** : L'application ne collecte aucune donnée avant le consentement. Le refus est possible et conduit à la fermeture de l'application
**Priorité** : Critique
**Lot** : Lot 1

### Feature 5.2 — Stockage local offline-first
**Description** : Toutes les données de stock et de santé sont stockées uniquement sur l'appareil de l'utilisateur. Aucune donnée de santé ne transite vers un serveur externe
**Critère d'acceptation** : L'application est pleinement fonctionnelle sans connexion réseau. Aucune requête réseau ne transporte des données personnelles de santé
**Priorité** : Critique
**Lot** : Lot 1

### Feature 5.3 — Export et suppression des données
**Description** : L'utilisateur peut à tout moment exporter l'intégralité de ses données ou demander leur suppression définitive
**Critère d'acceptation** : L'export produit un fichier lisible (JSON ou CSV). La suppression efface toutes les données locales et ne peut pas être annulée
**Priorité** : Haute
**Lot** : Lot 1
