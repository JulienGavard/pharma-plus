# Gouvernance — Pharma Plus

> Ce document décrit **comment le projet est gouverné**, pas ce que le produit fait (cela, c'est le rôle du PRD). Il est la carte du système : ses principes, ses artefacts, ses règles et son flux de travail. Toute personne qui le lit doit comprendre pourquoi le projet est organisé ainsi.

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

### 2.3 Distinguer les règles de sortie des règles de processus

- Une règle qui contraint la **sortie** (un fichier par épic, champ `source_prd` obligatoire, slug = fonction du titre) doit avoir un test correspondant.
- Une règle qui contraint le **processus** (ne pas lire le journal pendant la génération, immuabilité des ADR) n'est pas testable sur l'artefact — elle reste une garantie de procédure.

### 2.4 Tracer les décisions, jamais les effacer

Chaque décision structurante est un ADR immuable. On n'édite jamais une décision passée : on en ajoute une nouvelle qui la complète ou l'annule. L'historique reste toujours lisible.

---

## 3. L'architecture documentaire

### 3.1 La hiérarchie des ADR (Architecture Decision Records)

Les ADR gouvernent les artefacts par niveaux. Chaque niveau possède ses propres règles, dans son propre dossier.

| Niveau | Dossier | Gouverne |
|--------|---------|----------|
| **L0** | `adr-l0/` | Les règles de lecture et d'application des ADR eux-mêmes |
| **L1** | `adr-l1/` | Le PRD |
| **L2** | `adr-l2/` | Les épics |
| **L3** | `adr-l3/` | Les features |

Au sein de chaque dossier, l'ADR `000` est le template, et la numérotation croît dans l'ordre chronologique des décisions.

### 3.2 Les artefacts produit (`docs/`)

| Artefact | Rôle | Gouverné par |
|----------|------|--------------|
| `docs/PRD.md` | La vision produit : problème, utilisateur, objectif, critères, contraintes. Chaque élément porte un **identifiant stable** (`OBJ`, `CS-1`, `CL-2`…). | L1 |
| `docs/table-de-derivation.md` | Le **contrat de génération** : relie chaque élément du PRD aux épics qu'il justifie, et fixe le numéro stable de chaque épic. | L2 |
| `docs/epics/<slug>.md` | Un fichier par épic, avec frontmatter YAML machine-lisible. | L2 |
| `docs/features/<slug-épic>/<N-M>-<slug>.md` | Un fichier par feature, rangé sous son épic parent. | L3 |
| `docs/questions-ouvertes.md` | Les hypothèses et décisions encore ouvertes, tenues à l'écart de la vision stable. | L1 |

### 3.3 La mémoire transverse

- **`JOURNAL.md`** — le récit chronologique du projet, en langage naturel. Lisible par quiconque, il raconte *comment on en est arrivé là*. Tenu par le skill Archiviste. Jamais lu pendant une génération (pour ne pas biaiser le résultat).
- **Mémoire de session** (`.claude/.../memory/`) — l'état de reprise chargé automatiquement à chaque session : contexte, roadmap, préférences.

---

## 4. Les règles de gouvernance clés

| Règle | ADR | Effet |
|-------|-----|-------|
| **Lecture exhaustive et ordonnée des ADR** avant toute génération | L0-001 | La génération applique toujours toutes les règles à jour |
| **Gestion des conflits** : en cas de contradiction, on ne génère pas — on crée un fichier de conflit | L0-001 | Le système ne tranche jamais seul une contradiction métier |
| **Interdiction de lire le journal** pendant la génération | L0-002 | Le résultat dérive du PRD, pas de l'historique |
| **Immuabilité des ADR** : on ajoute, on ne modifie jamais | L0-003 | L'historique des décisions reste intact |
| **Isolation de la régénération** : ne pas lire les artefacts cibles avant de les réécrire | L0-001 | Pas de contamination par le contenu précédent |
| **Toute évolution du PRD passe par un ADR-L1** | L1-001 | La vision n'évolue jamais en silence |
| **Identifiants stables du PRD** | L1-006 | Traçabilité vérifiable par référence, pas par position |
| **Un fichier par épic / par feature** | L2-004, L3-001 | Navigation et traçabilité fine |
| **Numéro d'épic stable**, source = table de dérivation | L2-005 | Noms de fichiers des features déterministes |
| **Slug = fonction pure du titre** | L2-006 | Noms de fichiers reproductibles |
| **Frontmatter YAML** sur épics et features | L2-007, L3-002 | Métadonnées lisibles par une machine |
| **Aucune recommandation médicale ou posologique** | L1-006 (CL-4), L3-000 | Contrainte transversale, à tous les niveaux |

---

## 5. Le flux de travail

La production des spécifications suit toujours le même cycle, porté par le skill **Product Manager** :

```
Point d'entrée : « idée à explorer » ou « génération » ?
        │
        ├─ Idée → dialogue socratique (problème → utilisateur → specs) → met à jour le PRD (via ADR-L1)
        │
        └─ Génération :
              1. Lire tous les ADR (L0 → L1 → L2 → L3, ordre croissant)
              2. Construire et sauvegarder la table de dérivation (PRD → épics)
              3. Générer les épics (docs/epics/) puis les features (docs/features/)
              4. [cible] Lancer validate.py → si rouge, régénérer jusqu'au vert
              5. Comparer avec la génération précédente (diff lisible)
              6. Appeler l'Archiviste pour tracer la session
```

La règle qui fermera la boucle : **un artefact qui ne passe pas le harnais n'est pas livrable.** C'est ce qui transformera les ADR de « consignes qu'on espère respectées » en « invariants garantis ».

---

## 6. Les rôles (skills)

| Skill | Rôle |
|-------|------|
| **Product Manager** | Clarifie la vision par le dialogue, puis génère PRD / épics / features en appliquant les ADR. Ne descend jamais dans le détail sans demande explicite. |
| **Archiviste** | Tient le `JOURNAL.md` : à la fin de chaque session, ajoute une entrée narrative racontant les décisions prises et pourquoi. |

---

## 7. La roadmap du harnais

L'objectif de déterminisme se construit en quatre étapes, chacune cadrée par ses propres ADR.

| Étape | Contenu | État |
|-------|---------|------|
| **1. Nettoyer les bloquants** | PRD sous `docs/`, questions-ouvertes gouvernées, numéro d'épic fixé | ✅ Fait |
| **2. ID + frontmatter** | Identifiants stables du PRD, slug pur, frontmatter YAML des épics/features | ✅ Cadre posé (ADR + PRD instrumenté) |
| **3. Le validateur** | Un schéma par artefact + un script `validate.py` qui lève les violations | ⏳ À venir |
| **4. Brancher la boucle** | Intégrer `validate.py` au flux du Product Manager, lier chaque ADR-de-sortie à son test | ⏳ À venir |

### Les catégories de tests visées

- **Structurel** — frontmatter présente, champs obligatoires non vides, énumérations valides.
- **Référentiel** — tout `source_prd` existe dans le PRD ; tout épic parent existe.
- **Couverture** — chaque élément couvrable du PRD est porté par ≥ 1 épic et ≥ 1 feature ; les interdictions transversales (ex. CL-4) en sont exclues.
- **Contrat de dérivation** — bijection entre la table et les fichiers générés.
- **Nommage** — `slug == slugify(titre) == nom de fichier`, numérotation cohérente.
- **Unicité** — pas deux artefacts partageant le même `id` ou `slug`.

---

## 8. En une phrase

> Le projet traite ses propres spécifications comme du code : versionnées, gouvernées par des décisions immuables, dérivées d'une source unique, et — à terme — validées par des tests qui rendent leur génération reproductible.
