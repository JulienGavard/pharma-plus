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

## 2026-06-08 — Première génération en fichiers individuels

Nous avons régénéré les épics et features en appliquant pour la première fois ADR-L2-004 et ADR-L3-001 : chaque épic dans son propre fichier sous `docs/epics/`, chaque feature dans son propre fichier sous `docs/features/[epic]/`. Le contenu est identique à la génération précédente — 4 épics, 11 features (le "9" mentionné dans l'entrée précédente était une erreur de comptage), tous en Lot 1 — mais la structure de fichiers est entièrement différente. Cette génération valide que le système ADR permet de changer la forme des artefacts sans toucher à leur contenu.

## 2026-06-08 — Vision : un harnais de tests pour passer de la convergence au déterminisme

Nous avons posé l'objectif structurant de la suite du projet : passer d'une génération non déterministe à un résultat déterministe, non pas en espérant que le LLM produise toujours la même chose, mais en construisant un harnais de tests fonctionnels qui contraint la sortie. Cette entrée trace l'analyse complète qui guidera les prochains ADR.

### Le recadrage fondamental

Un harnais de tests ne rendra jamais la génération déterministe au sens « même sortie à l'octet près » — un LLM échantillonne, la prose variera toujours. Ce qu'il apporte, c'est de la **convergence** : il définit une *région d'acceptation* dans l'espace des sorties, et l'on régénère jusqu'à tomber dedans (« regenerate-until-green »). La conséquence est forte : **tout ce que l'on veut invariant doit devenir un test ; tout ce qui n'est pas testé reste libre de varier.** Le harnais n'est donc pas un accessoire de qualité — c'est la définition exécutable de « déterministe » pour le projet. Plus l'on encode de contraintes en tests, plus la région se resserre, plus le résultat est déterministe sur les dimensions qui comptent (structure, traçabilité, couverture, nommage), même si les phrases bougent.

Corollaire de gouvernance : un ADR qui contraint la **sortie** (un fichier par épic, champ Source PRD obligatoire…) doit avoir un test correspondant. Un ADR qui contraint le **processus** (ne pas lire le journal, immuabilité des ADR) n'est pas testable sur l'artefact — il reste une garantie de procédure. Nos ADR actuels mélangent les deux sans le distinguer.

### Le verrou actuel : des artefacts non lisibles par une machine

Les épics et features sont aujourd'hui de la prose à clés grasses (`**Source PRD** : Critère de succès 4 (< 30 secondes)`). Un test ne peut pas vérifier de façon fiable une traçabilité exprimée en langage naturel. C'est le point de levier numéro un, et tout le reste en dépend. Trois changements structurels, par ordre de leverage :

1. **Identifiant stable pour chaque élément du PRD.** « Critère de succès 4 » est positionnel — réordonner le PRD casse toutes les références. Passer à des ID stables : `PROB`, `OBJ`, `CS-1…CS-5` (critères de succès), `CL-1…` (contraintes légales), `CT-1…` (contraintes techniques), `CR-1…` (remboursement), `CB-1…` (business).

2. **Frontmatter YAML sur chaque épic et feature**, la prose restant dans le corps. Un épic porterait `id`, `slug`, `titre`, `priorite`, `lot`, `source_prd: [liste d'ID]` ; une feature porterait `id`, `epic` (parent), `slug`, `priorite`, `lot`, `source_prd`. La traçabilité vérifiée à l'œil devient alors une assertion triviale : tout `source_prd` cité existe dans le PRD ; tout `epic` parent existe ; tout critère de succès est couvert par au moins un épic et au moins une feature.

3. **Algorithme de slug spécifié comme fonction pure.** La règle actuelle (« accents supprimés, espaces → tirets ») est incomplète : `Suivi des économies & reste à charge` est devenu `...-et-...`, donc `&` → `et`, transformation écrite nulle part. Un test sur les noms de fichiers est impossible tant que le slug n'est pas une fonction déterministe complète (gestion de `&`, apostrophes, casse, accents, doubles tirets).

### La conception du harnais

Trois pièces : (1) un **schéma** par type d'artefact (JSON Schema ou équivalent) — la version dure des templates ADR-L*-000 aujourd'hui en prose ; (2) un **validateur exécutable** (un script `validate.py`) qui parse tous les artefacts et lève les violations ; (3) les **catégories de tests** :

- **Structurel** : frontmatter présente, champs obligatoires non vides, valeurs d'enum valides (priorité, lot).
- **Référentiel** : tout `source_prd` existe dans le PRD ; tout `epic` parent existe.
- **Couverture** : chaque critère de succès du PRD → au moins un épic ET au moins une feature ; les sections volontairement non couvertes sont déclarées explicitement (ex. « pas de recommandation médicale »).
- **Contrat de dérivation** : bijection table ↔ fichiers — tout épic de la table a un fichier, tout fichier figure dans la table. ADR-L2-001 dit déjà que la table *est* le contrat, mais rien ne le vérifie aujourd'hui.
- **Nommage** : `slug(titre) == nom de fichier` ; numérotation des features cohérente avec le numéro d'épic.
- **Unicité** : pas deux épics ou features partageant le même id ou slug.

### Intégration dans le workflow

La boucle du skill product-manager devient : lire PRD + ADR → construire la table → générer les artefacts → lancer `validate.py` → si rouge, corriger/régénérer puis revalider → si vert, diff + archiviste. La règle de gouvernance qui ferme la boucle : **un artefact qui ne passe pas le harnais n'est pas livrable.** C'est ce qui fait passer les ADR du statut de « consigne qu'on espère respectée » à « invariant garanti ».

### Points bloquants à nettoyer d'abord

Des incohérences qui feraient échouer le harnais dès le premier run : le PRD est à `PRD.md` (racine) alors que tous les ADR référencent `docs/PRD.md` ; les fichiers monolithiques `docs/epics.md` et `docs/features.md` existent encore alors qu'ADR-L2-004 / L3-001 les ont remplacés par les dossiers (deux sources de vérité) ; le numéro d'épic n'est défini nulle part alors que les features `1.x` le présupposent ; `questions-ouvertes.md` à la racine fait doublon avec la section « Questions ouvertes » du template PRD.

### Ordre recommandé

(1) Nettoyer les bloquants ; (2) retrofiter ID + frontmatter sur PRD, épics et features (touche le PRD → ADR-L1 obligatoire) ; (3) écrire le schéma + `validate.py` ; (4) brancher la boucle dans le skill et lier chaque ADR-de-sortie à son test. Chacune de ces étapes mérite probablement son propre ADR.

## 2026-06-09 — Étape 1 : nettoyage des bloquants

Nous avons exécuté la première étape de la roadmap du harnais en levant les incohérences structurelles. Le PRD, qui vivait à la racine alors que tous les ADR le référencent sous `docs/`, a été déplacé vers `docs/PRD.md` — simple mise en conformité, sans nouvel ADR. Le fichier `questions-ouvertes.md`, orphelin à la racine, a été déplacé sous `docs/` et explicitement gouverné par ADR-L1-005 : nous avons choisi de le garder comme artefact séparé plutôt que de le fondre dans le PRD, pour séparer nettement la vision stable des questions mouvantes. Enfin, ADR-L2-005 a fait de la table de dérivation la source d'autorité du numéro d'épic — jusque-là implicite, donc source silencieuse de non-déterminisme sur les noms de fichiers des features.

## 2026-06-09 — Étape 2 : identifiants stables et frontmatter machine-lisible

Nous avons réalisé le cœur du dispositif : rendre les artefacts lisibles par une machine. Quatre ADR ont été posés — ADR-L1-006 (identifiants stables des éléments du PRD : PROB, OBJ, CS-*, CL-*, CT-*, CR-*, CB-*), ADR-L2-006 (l'algorithme de slug défini comme fonction pure et partagée), ADR-L2-007 et ADR-L3-002 (frontmatter YAML des épics et features). Le PRD a été instrumenté avec ses ID et la table de dérivation passée au même vocabulaire. Une décision de conception importante : le slug est désormais une fonction pure du titre, jamais un choix libre — sinon une régénération rechoisirait les noms de fichiers et ruinerait le déterminisme.

En instrumentant les `source_prd`, nous avons découvert un trou de couverture — la contrainte CL-2 (hébergeur HDS si cloud) n'était portée par aucune feature — et l'avons rattachée au stockage local offline, qui est précisément ce qui décharge cette obligation. C'est la preuve par l'usage que le dispositif attrape ce qu'il doit attraper, avant même que le validateur existe.

## 2026-06-09 — Demander avant de modifier, et purge des artefacts générés

Deux décisions de méthode. D'abord, l'utilisateur a demandé à être consulté avant toute modification de fichier ; nous avons préféré une garantie au niveau du harnais (permissions `Edit`/`Write` en « ask » dans les settings locaux) plutôt que de compter sur la seule mémoire — le harnais affiche désormais un prompt avant chaque écriture. Ensuite, les épics et features générés ont été supprimés volontairement : ils sont régénérables à tout moment par le product-manager, et renaîtront cette fois directement avec leur frontmatter. Le cadre de l'étape 2 (ADR, PRD instrumenté, table) reste en place ; seuls les artefacts dérivés ont été vidés, en attendant leur régénération avant l'écriture du validateur.

## 2026-06-09 — Un document de gouvernance, et la grande clarification : trois natures de décision

Nous avons d'abord créé `docs/gouvernance.md`, une vue d'ensemble du système qui manquait : ni le journal (récit) ni les ADR (décisions atomiques) ne donnaient la carte complète des principes, artefacts, règles et flux de travail.

En le relisant, une question de fond a émergé : le mot « ADR » recouvrait en réalité trois natures de décision radicalement différentes — la **gouvernance** (comment on fabrique les specs), le **produit** (ce que Pharma Plus fait), et l'**architecture logicielle** (comment l'app est techniquement construite, nature qui n'avait aucun foyer alors qu'elle arrivera avec le code). La hiérarchie L0–L3 classait par artefact touché, pas par nature, si bien qu'un même dossier mélangeait gouvernance et produit. Nous avons tranché pour une séparation complète en **trois registres par nature** : `gouvernance/` (records GDR, niveaux L0–L3 conservés), `produit/` (records PDR), `architecture/` (records ADR, le terme retrouvant son sens canonique, dossier réservé jusqu'au démarrage du dev).

Deux décisions importantes accompagnent ce choix. D'une part, une **migration sanctionnée** de tous les records existants vers le registre de gouvernance, sauf la décision sur les économies (nature produit) qui rejoint `produit/` — le projet étant jeune et petit, c'est le moment le moins coûteux pour réorganiser. D'autre part, nous avons **assoupli la règle d'immuabilité** : désormais les **références croisées** d'un record peuvent être modifiées (le contenu décisionnel, lui, reste immuable), ce qui rend la migration possible sans laisser de citations caduques. Le record fondateur de cette taxonomie est `GDR-L0-004`.

## 2026-06-09 — Exécution de la migration et naissance du Gouverneur

Nous avons exécuté la migration décidée juste avant. Les ~20 records de gouvernance ont été déplacés vers `gouvernance/` (préfixe `gdr-`, niveaux conservés), la décision économies vers `produit/pdr-001`, et trois templates ont été posés (`pdr-000`, `adr-000`, plus le keystone `gdr-l0-004`). Une décision de structure a émergé en cours de route : le **PRD devient la racine du registre produit** (`produit/PRD.md`), puisqu'il est l'artefact produit fondateur autour duquel gravitent les PDR. Les anciens dossiers `adr-l*`, vidés, ont été supprimés.

Enfin, nous avons créé un nouveau skill, le **Gouverneur**, capable de régénérer `docs/gouvernance.md` à partir de l'état réel des trois registres. Particularité notable : contrairement à la règle d'isolation qui interdit de lire un artefact avant de le régénérer, le Gouverneur a explicitement le droit de relire la version précédente de `gouvernance.md` — car c'est un document de synthèse, pas un artefact dérivé du PRD, et la continuité de ton prime sur le risque de contamination. Reste à finaliser : l'alignement des titres internes des records (ADR → GDR) et la mise à jour des références dans `gouvernance.md`, la table de dérivation et le skill product-manager.

## 2026-06-09 — Finalisation de la migration et premier passage du Gouverneur

Nous avons bouclé la migration. Les 20 titres internes des records ont été alignés (`# ADR-L*` → `# GDR-L*`), la table de dérivation et le skill product-manager mis à jour (chemins `produit/PRD.md`, références `gdr-*`, routage produit → PDR, dossiers `docs/epics/` et `docs/features/`). Surtout, nous avons lancé le Gouverneur pour la première fois : il a régénéré `gouvernance.md` à partir des registres, faisant passer la carte d'un monde « ADR unique à quatre niveaux » à un monde « trois registres par nature » — preuve que le skill lit l'état réel et le restitue fidèlement.

## 2026-06-09 — Le Gouverneur devient gardien, et trois précisions de modèle

En relisant la carte, trois remarques ont fait évoluer le modèle, chacune actée par un GDR. D'abord, le **PRD n'évolue qu'en conséquence d'un PDR** (`gdr-l1-007`) : dans le dialogue avec le Product Manager, tout changement produit se matérialise d'abord par un PDR — le record primaire — et l'édition du PRD n'en est que la conséquence, jamais un acte autonome.

Ensuite, et c'est le plus structurant, le rôle du **Gouverneur a été redéfini** (`gdr-l0-005`) : il n'est plus un simple générateur de carte, mais le **gardien de la conformité GDR**. Il vérifie qu'aucune règle n'est enfreinte — par l'utilisateur, le Product Manager ou Claude — et, en cas d'infraction, il **stoppe la modification et journalise un conflit** dans `conflits/`, à traiter par le chef d'orchestre. Il ne corrige ni ne tranche seul ; la génération de `gouvernance.md` devient un sous-produit de cette vigilance.

Enfin, la **roadmap a été externalisée** dans `docs/roadmap.md` (`gdr-l0-006`), sur le modèle des questions ouvertes : un document mouvant tenu à l'écart des principes stables de la carte, pour ne pas régénérer celle-ci à chaque avancement. Le skill Gouverneur et `gouvernance.md` ont été mis à jour en conséquence.

## 2026-06-09 — Le Gouverneur passe en surveillance automatique

Nous avons opérationnalisé le rôle de gardien : une routine distante planifiée lance désormais le Gouverneur **chaque jour à 9h** (sur l'infrastructure cloud, indépendamment de toute session locale). À chaque passage, il vérifie la conformité du dépôt aux règles GDR, journalise un conflit en cas d'infraction, régénère la carte, et ouvre une Pull Request si quelque chose a changé. C'est le moment où la gouvernance cesse d'être seulement déclarative : une vigilance s'exécute toute seule. Une réserve subsiste : l'accès GitHub du dépôt n'est pas encore connecté, ce qui devra être réglé (`/web-setup`) avant que la routine puisse réellement cloner et ouvrir des PR. En parallèle, nous avons mis l'Archiviste lui-même en boucle (toutes les 30 minutes pendant la session) pour que le journal se tienne à jour sans y penser.

## 2026-06-09 — Début de l'étape 3 : le validateur

Nous avons attaqué le cœur du harnais, le script `validate.py`. Plutôt que de régénérer d'abord les épics/features, nous avons choisi d'écrire le validateur en premier (option B) : il encode, en Python, les six catégories de contrôle dérivées des GDR — structurel, référentiel, couverture, contrat de dérivation, nommage et unicité — pour les faire tourner d'abord « à vide ». Une exigence de lisibilité est apparue en chemin : le contrôle du contrat de dérivation, d'abord nommé « bijection table ↔ fichiers épics », sera reformulé en « correspondance exacte » — le harnais doit rester compréhensible par quiconque, pas seulement par un mathématicien. Une première version procédurale a tourné « à vide » et a correctement signalé 32 violations (couverture et dérivation).

## 2026-06-09 — Le code adopte la POO et le Domain-Driven Design

Au moment d'extraire les règles du validateur, une décision de fond a été prise sur la manière même de développer : tout le code de Pharma Plus suivra le paradigme **POO + Domain-Driven Design**. Concrètement, les objets métier sont nommés dans le langage du domaine, en français (`Slug`, `IdentifiantPrd`, `Epic`, `Feature`, `TableDeDerivation`, `RegleDeCouverture`, `RapportDeConformite`…), et la **logique métier est strictement séparée de la logique technique** (domaine sans I/O, infrastructure pour la lecture des fichiers et le rendu, application pour l'orchestration). Le code vivra dans `src/`, avec **un sous-dossier par sous-domaine** au sens DDD — le premier étant `src/harnais/`. Ce choix a fait abandonner deux approches plus techniques évoquées juste avant : le script procédural `validate.py` et l'externalisation des règles en schémas JSON, jugés trop éloignés du langage métier. La prochaine étape est la construction de ce sous-domaine, dont l'architecture en trois couches a été esquissée.

## 2026-06-09 — Le projet choisit Kotlin et active le registre architecture

En préparant le code, une question simple a tout débloqué : pourquoi Python ? La réponse honnête était « par défaut, jamais décidé ». Nous avons donc tranché délibérément : **Kotlin** pour tout le projet, harnais comme future app mobile offline-first — un stack unifié plutôt qu'un outil dans un langage et une app dans un autre. Surtout, nous avons reconnu que le choix d'un langage est une décision d'architecture logicielle : elle inaugure le registre `architecture/`, resté réservé jusqu'ici. Deux ADR sont nés ensemble — `adr-001` (Kotlin) et `adr-002` (les conventions POO + DDD, jusque-là tapies dans la mémoire de travail, désormais gouvernées). Le prototype Python a été supprimé sans regret.

Dans la foulée, nous avons monté l'échafaudage : un projet Gradle/Kotlin avec wrapper auto-suffisant, compilé et exécuté au vert sur un JDK 21 fraîchement installé. Le terrain est prêt pour écrire le harnais en DDD, cette fois dans un langage tenu pour acquis. Au passage, l'Archiviste s'est vu confier l'auto-démarrage de sa propre boucle d'archivage, pour ne plus dépendre d'un lancement manuel.

## 2026-06-09 — Le harnais prend corps en Kotlin DDD, avec ses tests

Nous avons implémenté l'étape 3 — le validateur — en Kotlin, selon l'architecture en trois couches décidée. Le **domaine** porte les objets métier nommés dans la langue du projet (`Slug`, `IdentifiantPrd`, `Epic`, `Feature`, `TableDeDerivation`, `Violation`, `RapportDeConformite`) et les six règles comme autant d'objets (`RegleDeStructure`, `-Reference`, `-Couverture`, `-Derivation`, `-Nommage`, `-DUnicite`) ; il ne connaît ni fichiers ni Markdown. L'**infrastructure** lit le dépôt et rend le rapport ; l'**application** orchestre. À la demande, nous avons écrit les **tests unitaires du domaine** — le `Slug` sur ses cas de référence et son idempotence, les value objects, et chaque règle prise séparément — tous au vert. Le harnais retrouve exactement les 32 violations « à vide » du prototype Python abandonné : l'équivalence est démontrée, mais cette fois sur un code lisible et testé.

## 2026-06-09 — La racine se range : trois registres sous `registres/` et des chemins centralisés

En voyant la racine s'encombrer, nous avons regroupé les trois registres de décision (`gouvernance/`, `produit/`, `architecture/`) sous un dossier parent **`registres/`**, qui colle enfin au vocabulaire de la taxonomie. Ce regroupement est acté par `gdr-l0-007`, qui précise l'emplacement défini par `gdr-l0-004` sans toucher à la nature des registres. Une frustration adjacente a trouvé sa réponse : les chemins étaient répétés en dur partout. Nous avons créé **`chemins.properties`**, source unique de vérité des chemins, que les skills consomment via des alias (`$GOUVERNANCE`, `$PRD`…) et que le harnais lit via `Chemins.kt` — désormais, un futur déplacement ne touchera que ce fichier. Tout a été réaligné (skills, carte de gouvernance, table, routine planifiée, mémoire), la build est restée verte, et nous avons profité de l'ampleur du chantier pour lever temporairement la confirmation par fichier, restaurée à la fin.

## 2026-06-09 — Tout le « non-code » sous `conception/`, et un README d'accueil

Poussant le rangement plus loin, nous avons créé un dossier parent **`conception/`** qui réunit tout ce qui n'est pas du code : les `registres/` (décisions), les `specifications/` (les artefacts dérivés — table, épics, features — désormais distincts des décisions et des documents) et `docs/` (la carte, la roadmap, les questions ouvertes). La racine ne garde plus que le code Kotlin, Gradle, `chemins.properties` et le journal. Ce déplacement d'ampleur, acté par `gdr-l0-008`, a confirmé le pari de la centralisation : il a suffi de modifier **`chemins.properties`** pour que le harnais et les skills suivent, sans toucher à leur logique. Enfin, nous avons écrit un **README** à la racine, porte d'entrée du dépôt, qui explique les deux facettes du projet : le produit Pharma Plus, et la méthode — rendre déterministe la génération de specs assistée par LLM.

## 2026-06-09 — Le Gouverneur apprend à critiquer l'organisation

Nous avons donné au Gouverneur une troisième mission, au-delà de la vérification des règles et de l'entretien de la carte : **porter un regard critique sur l'ensemble de l'organisation** et consigner ses remarques (`gdr-l0-009`). La distinction est nette et utile — une **infraction** à une règle est un *conflit*, bloquant, journalisé dans `conflits/` ; une **remarque d'amélioration** non-bloquante est une *question ouverte*. Ces remarques vont dans le fichier des questions ouvertes, sous une section dédiée, élargissant ce fichier (jusqu'ici purement produit) aux questions d'organisation. Le Gouverneur reste sans pouvoir d'action : il constate, bloque ou suggère, mais ne réorganise jamais — c'est au chef d'orchestre de trancher. La routine quotidienne a été mise à jour pour inclure cette critique.

## 2026-06-09 — Premiers remboursements de dette : chemins uniques et domaine non anémique

Lancé sur l'organisation, le Gouverneur a produit sept remarques honnêtes ; nous en avons traité deux. D'abord sa **remarque 1** : `chemins.properties` se voulait source unique, mais le harnais redéfinissait des chemins par défaut en dur. Nous avons supprimé ces défauts — un fichier ou une clé manquante échoue désormais explicitement, et c'est verrouillé par un test (`CheminsTest`). Plus de seconde source qui dérive en silence.

Ensuite, une critique de fond venue du chef d'orchestre lui-même : nos entités `Epic` et `Feature` étaient anémiques (de simples données, toute la logique au-dehors). Nous avons tranché pour un **modèle de domaine non anémique** (`adr-003`) : chaque entité porte désormais sa propre validation (`violationsStructurelles`, `violationsDeNommage`, `violationsDeReference`), et les règles correspondantes deviennent de minces orchestrateurs qui délèguent. Nous avons distingué ce qui appartient vraiment à une entité de ce qui est *transverse* (couverture, dérivation, unicité, et deux contrôles inter-objets gardés provisoirement) — ce dernier cas, probablement à confier à un **agrégat**, reste un chantier ouvert. Le comportement du harnais est inchangé (mêmes 32 violations), preuve que le refactor n'a fait que déplacer la logique au bon endroit. Au passage, la roadmap a reçu un nouveau chantier : un fichier d'index résumant les records de chaque registre.

## 2026-06-09 — L'agrégat prend ses responsabilités

Nous avons fermé le chantier ouvert juste avant : les invariants **transverses** — ceux qui lient plusieurs objets (couverture, dérivation, unicité, existence du parent, dossier↔slug) — appartiennent désormais à la **racine d'agrégat** `Specifications`, qui expose `verifierConformite()` et produit le rapport (`adr-004`). Conséquence forte : la couche `Regle` (interface + six classes) a été **supprimée**. Toute la logique de conformité vit maintenant à sa juste place — l'intrinsèque sur les entités, le transverse sur l'agrégat. L'orchestrateur d'application, qui ne valide plus rien, a été renommé `Validateur` → **`Harnais`** (il charge, interroge l'agrégat, rend le rapport). Comportement toujours identique (32 violations).

## 2026-06-09 — Un second gardien : le CTO veille sur le code

Le Gouverneur gardait tout, mais son champ est l'organisation, la démarche et le fonctionnel — pas le code. Nous avons donc créé un **CTO** (`gdr-l0-010`), gardien du **code applicatif** : il confronte `src/` aux **ADR** (Kotlin, DDD, non-anémie, agrégat) et à la qualité, bloque par un conflit en cas d'infraction, et consigne ses critiques non-bloquantes dans les questions ouvertes. Symétrie nette : le Gouverneur est aux GDR ce que le CTO est aux ADR. Lancé sur le harnais, le CTO l'a déclaré conforme et a relevé cinq remarques de code ; nous en avons traité trois sur-le-champ — factorisation des fabriques de `Violation` (via une interface `Artefact`), typage de la frontmatter (fin des casts non vérifiés), et test du rendu `RapportConsole`. Le harnais est désormais gardé des deux côtés : ses specs par le Gouverneur, son code par le CTO.
