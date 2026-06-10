# Questions ouvertes

## Données & Infrastructure

- [ ] Utilise-t-on la base CIP de l'ANSM ou une API tierce pour la reconnaissance des médicaments ?
- [ ] Le stockage des données est-il local (offline-first) ou cloud ? Si cloud, faut-il un hébergeur HDS ?
- [ ] Comment gérer les boîtes sans code-barres lisible ou les médicaments génériques mal référencés ?

## Produit & UX

- [ ] Quel est le seuil de stock estimé en dessous duquel on alerte l'utilisateur ?
- [ ] Comment encourage-t-on l'utilisateur à déclarer ses prises sans que ça devienne une contrainte ?

## Hypothèses à valider

- [ ] Les utilisateurs acceptent de scanner une boîte au retour de la pharmacie (friction à l'entrée des données)
- [ ] Les codes-barres des boîtes de médicaments contiennent suffisamment d'information pour auto-compléter les champs
- [ ] L'utilisateur pense à ouvrir l'application avant d'aller à la pharmacie
- [ ] La valeur perçue est suffisante pour justifier l'effort de maintenir l'inventaire à jour

## Remarques du Gouverneur — organisation

*Remarques non-bloquantes sur l'organisation du dépôt (`gdr-l0-009`). Ce sont des propositions pour le chef d'orchestre, pas des actions ni des infractions.*

- [x] **Double source de chemins.** ✅ *Traité (2026-06-09)* : les défauts en dur de `infrastructure/Chemins.kt` sont supprimés ; `chemins.properties` est l'unique source. Un fichier ou une clé manquante échoue désormais explicitement (couvert par `CheminsTest`).
- [x] **Couverture de tests partielle.** ✅ *Traité (2026-06-09)* : l'infrastructure est désormais testée — `LecteurDeFrontmatterTest` (parsing frontmatter) et `DepotDeSpecificationsTest` (PRD, table, épics, features depuis le système de fichiers). Restent non couverts : `RapportConsole` (rendu) et `Harnais` (orchestration), à faible logique.
- [ ] **Toutes les règles GDR de sortie ne sont pas encore testées.** Le harnais valide les épics/features/table, mais pas la conformité des records eux-mêmes (préfixes `gdr-`/`pdr-`/`adr-`, niveaux, templates). Ces règles restent « espérées » plutôt que « garanties » — c'est l'objet de l'étape 4.
- [ ] **Le PRD est rangé parmi les décisions** (`registres/produit/`) alors que `gdr-l1-007` pose qu'il *découle* des PDR. Est-ce une décision, ou un artefact dérivé qui aurait sa place dans `specifications/` ? Frontière conceptuelle à clarifier.
- [ ] **Régénération de la carte à chaque audit.** La Partie B du Gouverneur réécrit `gouvernance.md` à chaque passage ; sur la routine quotidienne, cela peut produire des diffs de prose sans changement de fond — la variabilité LLM que le projet combat par ailleurs. Ne régénérer que sur changement réel des registres ?
- [ ] **`JOURNAL.md` à la racine** alors que tout le reste du « non-code » est sous `conception/`. Incohérence d'emplacement mineure à trancher.
- [ ] **`questions-ouvertes` mélange deux portées** (produit via `gdr-l1-005`, organisation via `gdr-l0-009`). La séparation par sections suffit-elle si le volume croît ?
