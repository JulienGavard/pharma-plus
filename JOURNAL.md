# Journal de bord — Pharma Plus

Ce document retrace les décisions importantes prises au cours du développement du projet.

## 2026-06-08 — Découverte de Claude Code et premiers pas sur Pharma Plus

Nous avons démarré le projet Pharma Plus en découvrant Claude Code ce jour. Pour structurer la collaboration, nous avons créé des skills dédiés — notamment un skill Product Manager — afin de cadrer le rôle de Claude dans les échanges.

Le premier travail a consisté à explorer le périmètre du projet via un jeu de questions ouvertes, ce qui a abouti à la création de `PRD.md`, le document de référence produit. Dans la foulée, Claude avait également généré des user stories et des fonctionnalités détaillées, mais il est apparu que cela allait trop loin trop vite. Nous avons décidé de freiner et de rester au niveau du PRD pour l'instant, sans descendre dans le détail des US et features avant d'en avoir besoin.

Cette décision de rythme est importante : mieux vaut consolider la vision avant de décomposer en livrables.

## 2026-06-08 — Structuration des livrables du skill Product Manager et règle ADR-L1

Nous avons précisé ce que le skill Product Manager doit produire : trois fichiers distincts — `docs/PRD.md`, `docs/epics.md` et `docs/features.md` — plutôt qu'un bloc monolithique. Cette séparation reflète la granularité naturelle du travail produit : la vision (PRD), les axes de valeur (épics), et les capacités détaillées (features).

Nous avons également introduit une règle de gouvernance importante : toute modification du PRD après sa création initiale doit faire l'objet d'un ADR de niveau 1, stocké dans le dossier `adr-l1/`. Le format de cet ADR est intégré directement dans le skill, avec des champs explicites pour documenter ce qui change, pourquoi, et l'impact sur les épics et features existantes. L'idée derrière cette règle est de tracer les évolutions de la vision produit comme on tracerait des décisions d'architecture — car un changement de PRD est, en substance, une décision structurante.

## 2026-06-08 — Ajout de la dimension financière et génération des épics et features

En relisant le PRD, il est apparu qu'une dimension importante n'avait pas été adressée : lorsqu'un utilisateur évite d'acheter un médicament qu'il possède déjà, il économise réellement de l'argent sur son reste à charge. Nous avons décidé d'intégrer cette estimation dans le produit. La règle ADR-L1 a été appliquée pour la première fois : l'ADR-L1-001 documente ce changement et ses impacts.

La discussion sur le reste à charge a révélé une tension entre l'idéal (automatisation complète via l'assureur mutuelle) et le réalisable en V1 (saisie manuelle du taux mutuelle par l'utilisateur). Nous avons tranché en faveur d'une approche progressive, ce qui a conduit à introduire une notion d'allotissement dans les templates du skill : chaque épic et chaque feature porte désormais un champ "Lot" indiquant dans quelle vague de livraison elle sera développée. Cette notion n'apparaît pas dans le PRD, qui reste au niveau de la vision.

Sur cette base, nous avons généré les premiers épics et features du projet — 5 épics et 16 features — couvrant la gestion du stock, les alertes, l'intégration CIP/ANSM, l'estimation des économies et la conformité RGPD. La Feature 4.4 (automatisation du taux mutuelle) est la seule à appartenir au Lot 2.

Enfin, nous avons décidé que le skill Product Manager appellera systématiquement l'Archiviste à la fin de chaque session, pour que les décisions soient tracées sans avoir à y penser.

## 2026-06-08 — Architecture des ADR sur trois niveaux et déterminisme de la génération

Nous avons restructuré en profondeur la gouvernance des artefacts produit autour d'une hiérarchie d'ADR à trois niveaux : L1 pour le PRD, L2 pour les épics, L3 pour les features. Chaque niveau dispose de ses propres documents de référence — un template (ADR-X-000) et des règles de génération — stockés dans les dossiers `adr-l1/`, `adr-l2/` et `adr-l3/`. Le skill product-manager ne contient plus aucun template inline : il délègue entièrement à ces ADR.

La décision centrale de cette session est d'ordre épistémique : nous voulons que deux relances du skill sur le même PRD produisent exactement les mêmes épics et features. Pour y parvenir, nous avons introduit une table de dérivation obligatoire — construite avant toute génération — qui relie chaque section du PRD aux épics qu'elle justifie. Aucun épic, aucune feature ne peut exister sans être ancré dans cette table. Le champ `Source PRD` sur chaque épic et feature rend cet ancrage visible et vérifiable.

Nous avons également clarifié que la notion d'allotissement (Lot) appartient exclusivement aux niveaux L2 et L3 — elle n'a pas sa place dans le PRD. Enfin, les fichiers ADR-L1 ont été renommés pour suivre une numérotation cohérente : ADR-L1-000 (template PRD), ADR-L1-001 (format des changements), ADR-L1-002 et au-delà pour les changements réels.

## 2026-06-08 — Introduction du niveau L0 et règle de gestion des conflits

Nous avons ajouté un niveau zéro à la hiérarchie des ADR : `adr-l0/` gouverne les règles de lecture et d'application des ADR eux-mêmes. L'ADR-L0-001 formalise trois principes : lecture exhaustive de tous les fichiers d'un dossier avant génération, ordre de lecture numérique croissant, et exhaustivité garantie même pour les ADR ajoutés après la création du skill.

La décision la plus significative de cet échange porte sur la gestion des conflits. Nous avons explicitement refusé que le système tente de résoudre seul une contradiction entre deux ADR. À la place, toute génération est suspendue et un fichier de conflit est créé dans `conflits/` pour être résolu par le chef d'orchestre. Cette règle traduit un choix de gouvernance clair : l'autonomie du système a des limites, et les contradictions dans les règles métier ne sont pas de son ressort.

## 2026-06-08 — Règle d'isolation de la régénération

En tentant de régénérer les épics et features avec les nouvelles règles ADR, nous avons identifié un risque de contamination : si le système lit les fichiers existants avant de les réécrire, il risque d'être influencé par leur contenu plutôt que de dériver purement depuis le PRD et les ADR. Nous avons donc ajouté une interdiction explicite dans le skill : `docs/epics.md` et `docs/features.md` ne doivent jamais être lus avant une régénération. La source unique de vérité est le PRD lu à travers le prisme des ADR — rien d'autre.

## 2026-06-08 — Première régénération propre sous gouvernance ADR complète

Nous avons régénéré `docs/epics.md` et `docs/features.md` en appliquant pour la première fois l'ensemble du dispositif ADR mis en place lors des sessions précédentes. Les fichiers existants avaient été supprimés volontairement afin d'éliminer toute contamination. La génération a démarré par la lecture ordonnée des ADR-L0, L2 et L3, puis par la construction de la table de dérivation PRD → épics avant tout écriture.

Le résultat est sensiblement différent de la première tentative : 4 épics au lieu de 5, et 9 features au lieu de 16, tous en Lot 1. La réduction n'est pas un appauvrissement — elle reflète une dérivation plus stricte, ancrée uniquement dans ce que le PRD formule explicitement. L'épic "Intégration CIP/ANSM" a notamment disparu en tant qu'entité autonome : il s'agissait d'une contrainte technique transversale, non d'un axe de valeur utilisateur, et elle est désormais correctement absorbée dans les épics "Inventaire du stock" et "Suivi des économies". De même, la feature 4.4 du Lot 2 (automatisation du taux mutuelle) n'a pas été recréée, faute d'ancrage explicite dans le PRD actuel.

Ce cycle complet — isolation, lecture des ADR, table de dérivation, génération — est la preuve que le dispositif de gouvernance fonctionne : le même PRD, relu avec le même prisme, produit une structure cohérente et traçable.

## 2026-06-08 — Deux nouvelles règles de gouvernance L0 : journal et immutabilité

Nous avons ajouté deux ADR de niveau L0 qui renforcent l'intégrité du système de gouvernance. Le premier — ADR-L0-002 — formalise l'interdiction de lire `JOURNAL.md` lors de la génération des épics et features. La raison est simple : le journal raconte ce qui a été produit par le passé, et le lire avant de générer risque d'ancrer le résultat dans l'historique plutôt que dans le PRD. Si une décision passée doit contraindre une génération future, elle doit passer par un ADR — pas par le récit.

Le second — ADR-L0-003 — pose le principe d'immutabilité des ADR : un ADR existant ne peut jamais être modifié, quel que soit son niveau. Toute évolution donne lieu à un nouvel ADR, numéroté dans la continuité. Ce principe est apparu naturellement : en tentant de modifier ADR-L0-001 pour y ajouter la règle sur le journal, l'utilisateur a refusé et demandé de créer un nouvel ADR à la place — geste qui a lui-même illustré et justifié ADR-L0-003. La règle s'applique d'ailleurs à elle-même : ADR-L0-003 ne peut pas être modifié.

## 2026-06-08 — Affinements des skills : point d'entrée et comparaison post-génération

Nous avons affiné le skill Product Manager sur deux points. D'abord, son point d'entrée : plutôt que de poser une question ouverte ("où en es-tu avec ton idée ?"), il pose désormais une alternative directe — explorer une idée ou lancer la génération des épics et features. Ce changement évite les tours de dialogue inutiles quand le PRD est déjà validé et que l'on veut simplement régénérer les artefacts.

Ensuite, une étape de comparaison a été ajoutée après chaque génération : le skill lit le journal pour retrouver la dernière génération documentée et présente un diff en langage naturel — épics apparus ou disparus, features déplacées entre lots. Cette étape comble un manque : jusqu'ici, il fallait comparer soi-même les fichiers pour comprendre ce qui avait changé. Notons que cette lecture du journal intervient après la génération, ce qui reste conforme à ADR-L0-002 qui interdit uniquement la lecture pendant la génération.

## 2026-06-08 — Un fichier par épic et par feature : correction de niveau et première application d'ADR-L0-003

Nous avons décidé que chaque épic et chaque feature serait désormais écrit dans son propre fichier, plutôt que regroupés dans `docs/epics.md` et `docs/features.md`. La règle pour les épics va dans `adr-l2/`, celle pour les features dans `adr-l3/`. Cependant, par erreur, les premiers ADR ont été créés au mauvais niveau — la règle sur les épics a atterri en L1 (qui gouverne le PRD), et celle sur les features en L2 (qui gouverne les épics).

C'est la première fois qu'ADR-L0-003 s'est appliqué comme contrainte réelle : impossible de corriger les mauvais ADR en les modifiant. Nous avons donc créé des ADR d'annulation dans les mêmes dossiers (ADR-L1-004 et ADR-L2-003), puis les deux ADR corrects aux bons niveaux (ADR-L2-004 et ADR-L3-001). L'utilisateur a ensuite demandé d'ajouter un statut "Annulé" directement sur les mauvais ADR — ce qui a également été refusé pour la même raison. Il a accepté cette cohérence : l'immuabilité s'applique même quand c'est inconfortable.
