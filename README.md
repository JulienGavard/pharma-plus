# Pharma Plus

> Un dépôt **deux-en-un** : un **produit** (une application de suivi de pharmacie familiale) et, surtout, une **méthode** pour rendre *déterministe* la génération de ses spécifications assistée par LLM.

---

## En une phrase

On traite les spécifications d'un produit **comme du code** : versionnées, gouvernées par des décisions immuables rangées par nature, dérivées d'une source unique, et — à terme — validées par des tests qui rendent leur génération reproductible.

---

## Les deux facettes

### 1. Le produit — Pharma Plus

Une application **offline-first** qui donne à l'utilisateur une visibilité permanente sur son armoire à pharmacie : ce qu'il possède, en quelle quantité, jusqu'à quand, et combien il économise sur son reste à charge en évitant les achats inutiles. La vision complète est dans le **PRD** ([`conception/registres/produit/PRD.md`](conception/registres/produit/PRD.md)).

### 2. La méthode — une génération de specs déterministe

Un LLM ne produit jamais deux fois la même sortie. Plutôt que de subir cette variabilité, on la **contraint** :

- **Convergence, pas reproduction à l'octet près.** Un harnais de tests définit une *région d'acceptation* ; on régénère jusqu'à tomber dedans (*regenerate-until-green*).
- **Tout ce qui doit être invariant devient un test.** Ce qui n'est pas testé reste libre de varier.
- **Chaque décision est un record immuable.** On n'efface jamais une décision : on en ajoute une nouvelle.

La carte complète du système : [`conception/docs/gouvernance.md`](conception/docs/gouvernance.md). Le récit de sa construction, décision après décision : [`JOURNAL.md`](JOURNAL.md).

---

## Structure du dépôt

```
conception/              ← tout le « non-code »
├── registres/           décisions, par nature
│   ├── gouvernance/     GDR — comment on fabrique les specs
│   ├── produit/         PRD (vision) + PDR — ce que le produit fait
│   └── architecture/    ADR — comment l'app est techniquement construite
├── specifications/      artefacts dérivés : table de dérivation, épics, features
└── docs/                carte de gouvernance, roadmap, questions ouvertes

src/                     ← le code (le harnais de validation, en Kotlin)
chemins.properties       source unique de vérité des chemins du dépôt
JOURNAL.md               le récit chronologique du projet
```

---

## Les trois registres de décision

Chaque décision a une **nature**, et donc un registre (voir `gdr-l0-004`) :

| Record | Nature | Exemple |
|--------|--------|---------|
| **GDR** (Governance Decision Record) | Comment on fabrique les specs | « un fichier par épic », « slug = fonction pure du titre » |
| **PDR** (Product Decision Record) | Ce que le produit fait | « estimer les économies sur le reste à charge » |
| **ADR** (Architecture Decision Record) | Comment l'app est construite | « le langage du projet est Kotlin » |

---

## Le harnais de validation

Un programme Kotlin ([`src/`](src/)) qui vérifie que les specs respectent les règles de gouvernance, sur six catégories : **structurel, référentiel, couverture, contrat de dérivation, nommage, unicité**. Il est écrit en **Domain-Driven Design** (objets métier nommés dans le langage du domaine, logique métier isolée de la technique).

```bash
# Pré-requis : un JDK 21
./gradlew build     # compile + lance les tests unitaires du domaine
./gradlew run       # valide les specs du dépôt (code de sortie 0 = conforme, 1 = violations)
```

---

## Les rôles (skills)

Trois assistants spécialisés pilotent le projet :

- **Product Manager** — clarifie la vision par le dialogue, puis génère le PRD, les épics et les features en appliquant les GDR.
- **Archiviste** — tient le `JOURNAL.md` : à chaque session, une entrée narrative des décisions prises et pourquoi.
- **Gouverneur** — gardien de la conformité : vérifie qu'aucune règle GDR n'est enfreinte, journalise les conflits, et maintient la carte de gouvernance.

---

## Pour aller plus loin

- **La carte du système** → [`conception/docs/gouvernance.md`](conception/docs/gouvernance.md)
- **La vision produit** → [`conception/registres/produit/PRD.md`](conception/registres/produit/PRD.md)
- **La feuille de route** → [`conception/docs/roadmap.md`](conception/docs/roadmap.md)
- **Le récit du projet** → [`JOURNAL.md`](JOURNAL.md)

---

*Ce projet ne formule jamais de recommandation médicale ou posologique.*
