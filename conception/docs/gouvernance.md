# Gouvernance — Pharma Plus

> Ce document décrit **comment le projet est gouverné**, pas ce que le produit fait (cela, c'est le rôle du PRD). Il est la carte du système : ses principes, ses artefacts, ses règles et son flux de travail. Il est régénéré par le skill **Gouverneur** à partir de l'état réel des registres.

---

## 1. L'intention

Le projet vise un objectif inhabituel : **rendre déterministe une production assistée par LLM.** Les spécifications produit (PRD → épics → features) sont générées par un modèle de langage, donc non déterministes par nature — deux relances sur la même entrée peuvent diverger. Plutôt que de subir cette variabilité, nous la contraignons par une gouvernance explicite et, à terme, par un harnais de tests.

L'organisation du projet est entièrement au service de cet objectif.

---

## 2. Les principes fondateurs

### 2.1 Convergence, pas reproduction à l'octet près

Un harnais de tests ne rend pas le LLM déterministe — il définit une **région d'acceptation** dans l'espace des sorties, et l'on régénère jusqu'à tomber dedans (*regenerate-until-green*). Le résultat est *fonctionnellement* déterministe : la structure, la traçabilité, la couverture et le nommage sont invariants, même si la prose varie.

### 2.2 Tout ce qui doit être invariant doit devenir un test

Corollaire direct : **ce qui n'est pas testé reste libre de varier.** Le harnais n'est donc pas un filet de qualité optionnel — c'est la définition exécutable de « déterministe » pour ce projet. Plus on encode de contraintes en tests, plus la région d'acceptation se resserre.

### 2.3 Deux axes de distinction orthogonaux

Toute décision se situe sur **deux axes indépendants** :

- **La nature** — ce que la décision touche :
  - *Gouvernance* (GDR) : comment on fabrique les specs.
  - *Produit* (PDR) : ce que Pharma Plus fait.
  - *Architecture logicielle* (ADR) : comment l'app est techniquement construite.
- **La testabilité** — comment on la fait respecter :
  - *Règle de sortie* : contraint l'artefact produit → un test peut la vérifier.
  - *Règle de processus* : contraint la façon de travailler → garantie de procédure, non testable sur l'artefact.

Les deux axes sont indépendants : une règle de gouvernance peut être de sortie (« un fichier par épic ») ou de processus (« ne pas lire le journal pendant la génération »).

### 2.4 Tracer les décisions, jamais les effacer

Chaque décision est un record immuable. On n'édite jamais le **contenu décisionnel** d'un record : on en ajoute un nouveau qui le complète ou l'annule. Seules les **références croisées** (citations d'autres records, chemins de fichiers) peuvent être mises à jour — assouplissement introduit par `gouvernance/gdr-l0-004` pour permettre les réorganisations sans laisser de citations caduques.

---

## 3. L'architecture documentaire

> Tout le « non-code » est regroupé sous **`conception/`** (registres, spécifications, docs) ; le code vit sous `src/` avec les fichiers Gradle (`gdr-l0-008`). Les chemins sont centralisés dans `chemins.properties`.

### 3.1 Les trois registres par nature

La taxonomie est définie par `conception/registres/gouvernance/gdr-l0-004`. Les trois registres sont regroupés sous `conception/registres/`. Chaque nature de décision a son registre.

| Registre | Dossier | Type de record | Niveaux | Format de fichier |
|----------|---------|----------------|---------|-------------------|
| **Gouvernance** | `conception/registres/gouvernance/` | **GDR** (Governance Decision Record) | Oui (L0–L3) | `gdr-lX-NNN-slug.md` |
| **Produit** | `conception/registres/produit/` | **PDR** (Product Decision Record) | Non | `pdr-NNN-slug.md` |
| **Architecture logicielle** | `conception/registres/architecture/` | **ADR** (Architecture Decision Record) | Non | `adr-NNN-slug.md` |

Les niveaux du registre de gouvernance : `L0` gouverne les records eux-mêmes, `L1` le PRD, `L2` les épics, `L3` les features. Le registre architecture est **réservé** (template seul) jusqu'au démarrage du développement.

### 3.2 Les artefacts

| Artefact | Rôle | Gouverné par |
|----------|------|--------------|
| `conception/registres/produit/PRD.md` | Racine du registre produit : la vision (problème, utilisateur, objectif, critères, contraintes). Chaque élément porte un **identifiant stable** (`OBJ`, `CS-1`, `CL-2`…). Édité uniquement en conséquence d'un PDR. | GDR-L1 |
| `conception/registres/produit/pdr-NNN-*.md` | Décisions produit (ex. `pdr-001` : estimation des économies). | GDR-L0-004 (routage) |
| `conception/specifications/table-de-derivation.md` | Le **contrat de génération** : relie chaque élément du PRD aux épics, et fixe le numéro stable de chaque épic. | GDR-L2 |
| `conception/specifications/epics/<slug>.md` | Un fichier par épic, avec frontmatter YAML machine-lisible. | GDR-L2 |
| `conception/specifications/features/<slug-épic>/<N-M>-<slug>.md` | Un fichier par feature, rangé sous son épic parent. | GDR-L3 |
| `conception/docs/questions-ouvertes.md` | Les hypothèses et décisions ouvertes, à l'écart de la vision stable. | GDR-L1-005 |
| `conception/docs/roadmap.md` | La roadmap du harnais (document mouvant), externalisée de cette carte. | GDR-L0-006 |
| `conception/registres/architecture/adr-NNN-*.md` | Décisions techniques de réalisation (à venir). | GDR-L0-004 (routage) |

### 3.3 La mémoire transverse

- **`JOURNAL.md`** (racine) — le récit chronologique du projet, en langage naturel. Tenu par le skill Archiviste. Jamais lu pendant une génération d'épics/features (pour ne pas biaiser le résultat).
- **`conception/docs/gouvernance.md`** — la présente carte, régénérée par le Gouverneur.
- **Mémoire de session** — l'état de reprise chargé automatiquement : contexte, roadmap, préférences.

---

## 4. Les règles de gouvernance clés

| Règle | Record source | Effet |
|-------|---------------|-------|
| Lecture exhaustive et ordonnée des records avant génération | `gdr-l0-001` | La génération applique toutes les règles à jour |
| En cas de conflit entre records, ne pas générer — créer un fichier de conflit | `gdr-l0-001` | Le système ne tranche jamais seul une contradiction métier |
| Interdiction de lire le journal pendant la génération | `gdr-l0-002` | Le résultat dérive du PRD, pas de l'historique |
| Immuabilité du contenu décisionnel ; références croisées modifiables | `gdr-l0-003`, `gdr-l0-004` | L'historique reste intact, les réorganisations restent cohérentes |
| Taxonomie en trois registres + routage par nature | `gdr-l0-004` | Gouvernance, produit et logiciel ne se mélangent plus |
| Le Gouverneur vérifie la conformité GDR ; bloque et journalise un conflit en cas d'infraction | `gdr-l0-005` | Les règles deviennent contrôlées, pas seulement espérées |
| Externalisation de la roadmap | `gdr-l0-006` | La carte stable n'est pas régénérée à chaque avancement |
| Le PRD n'évolue qu'en conséquence d'un PDR | `gdr-l1-007`, `gdr-l0-004` | La vision ne change jamais en silence ; le PDR est le record primaire |
| Externalisation des questions ouvertes | `gdr-l1-005` | La vision stable reste séparée des questions mouvantes |
| Identifiants stables du PRD | `gdr-l1-006` | Traçabilité vérifiable par référence, pas par position |
| Table de dérivation = contrat de génération | `gdr-l2-001` | Même PRD → mêmes épics, traçable |
| Un fichier par épic / par feature | `gdr-l2-004`, `gdr-l3-001` | Navigation et traçabilité fine |
| Numéro d'épic stable, source = table de dérivation | `gdr-l2-005` | Noms de fichiers des features déterministes |
| Slug = fonction pure du titre | `gdr-l2-006` | Noms de fichiers reproductibles |
| Frontmatter YAML sur épics et features | `gdr-l2-007`, `gdr-l3-002` | Métadonnées lisibles par une machine |
| Aucune recommandation médicale ou posologique | `gdr-l1-006` (CL-4), `gdr-l3-000` | Contrainte transversale, à tous les niveaux |

---

## 5. Le flux de travail

La production des spécifications suit toujours le même cycle, porté par le skill **Product Manager** :

```
Point d'entrée : « idée à explorer » ou « génération » ?
        │
        ├─ Idée → dialogue socratique (problème → utilisateur → specs)
        │         → chaque changement produit devient un PDR ;
        │           le PRD est édité en conséquence, jamais directement (gdr-l1-007)
        │
        └─ Génération :
              1. Lire tous les GDR (L0 → L3, ordre croissant)
              2. Construire et sauvegarder la table de dérivation (PRD → épics)
              3. Générer les épics (docs/epics/) puis les features (docs/features/)
              4. [cible] Lancer validate.py → si rouge, régénérer jusqu'au vert
              5. Comparer avec la génération précédente (diff lisible)
              6. Appeler l'Archiviste pour tracer la session
```

La règle qui fermera la boucle : **un artefact qui ne passe pas le harnais n'est pas livrable.** C'est ce qui transformera les GDR de « consignes qu'on espère respectées » en « invariants garantis » — un contrôle que le Gouverneur exerce déjà manuellement (`gdr-l0-005`).

---

## 6. Les rôles (skills)

| Skill | Rôle |
|-------|------|
| **Product Manager** | Clarifie la vision par le dialogue, puis génère PRD / épics / features en appliquant les GDR. Tout changement produit devient un PDR ; il ne descend jamais dans le détail sans demande explicite. |
| **Archiviste** | Tient le `JOURNAL.md` : à la fin de chaque session, ajoute une entrée narrative racontant les décisions prises et pourquoi. |
| **Gouverneur** | **Gardien de la conformité GDR** : vérifie qu'aucune règle n'est enfreinte (par l'utilisateur, le Product Manager ou Claude) ; en cas d'infraction, **stoppe la modification et journalise un conflit** dans `conflits/` (`gdr-l0-005`). Maintient en sous-produit cette carte `docs/gouvernance.md`. |

---

## 7. La roadmap

La roadmap du harnais est **externalisée** dans [`docs/roadmap.md`](roadmap.md) (`gdr-l0-006`) : c'est un document mouvant, dont l'état évolue à chaque avancement, tenu à l'écart des principes stables de cette carte.

---

## 8. En une phrase

> Le projet traite ses propres spécifications comme du code : versionnées, gouvernées par des décisions immuables rangées par nature, dérivées d'une source unique, et — à terme — validées par des tests qui rendent leur génération reproductible.
