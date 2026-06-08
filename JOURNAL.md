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
