# GDR-L0-008 — Regroupement du « non-code » sous `conception/`

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Après le regroupement des registres sous `registres/` (`gdr-l0-007`), la racine mélangeait encore plusieurs mondes : les registres de décision, les documents (carte, roadmap), les spécifications dérivées (épics, features, table) éparpillées dans `docs/`, et le projet de code (`src/`, Gradle). La frontière entre **ce qui définit/gouverne le produit** et **ce qui le réalise** n'était pas matérialisée.

## Décision

### 1. Un dossier parent `conception/`

Tout le « non-code » est regroupé sous **`conception/`** — par opposition à la réalisation (le code sous `src/` et les fichiers Gradle, qui restent à la racine car Gradle l'exige).

```
conception/
├── registres/        décisions (GDR, PDR, ADR) — cf. gdr-l0-004, gdr-l0-007
├── specifications/   artefacts dérivés : table de dérivation, epics/, features/
└── docs/             carte de gouvernance, roadmap, questions ouvertes
```

### 2. Les spécifications dérivées dans `specifications/`

Les artefacts **dérivés** du PRD (table de dérivation, épics, features) sont distingués des **décisions** (registres) et des **documents** (docs) : ils vivent dans `conception/specifications/`. Ils sont régénérés par le Product Manager.

### 3. Source unique des chemins

`chemins.properties` reste la **source unique de vérité** des chemins (`gdr-l0-007`). Ce déplacement n'a nécessité de mettre à jour QUE ce fichier pour le harnais et les skills (qui suivent par alias) — la preuve que la centralisation tient ses promesses.

## Conséquences

- `conception/` créé ; `registres/`, `docs/` y sont déplacés ; `specifications/` accueille la table (et les épics/features à venir).
- `chemins.properties` mis à jour (chemins `conception/...`) ; valeurs par défaut de `infrastructure/Chemins.kt` alignées.
- `conception/docs/gouvernance.md` et la table mis à jour avec les chemins résolus ; la routine planifiée et la mémoire réalignées.
- Le harnais reste vert ; comportement inchangé (mêmes 32 violations à vide).
- Les citations historiques d'anciens chemins restent valides via l'équivalence établie par `gdr-l0-004` (références croisées modifiables).
