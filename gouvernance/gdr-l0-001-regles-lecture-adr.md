# GDR-L0-001 — Règles de lecture et d'application des ADR lors de la génération

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

Les ADR (Architecture Decision Records) sont organisés en niveaux hiérarchiques : L1 (PRD), L2 (épics), L3 (features). Pour garantir que ces règles sont effectivement appliquées lors de chaque génération, des méta-règles de lecture sont nécessaires.

## Règles

### 1. Lecture obligatoire avant génération

Avant toute génération d'artefact, lire l'intégralité des ADR du niveau correspondant :

- Avant de générer le PRD → lire tous les fichiers de `adr-l1/`
- Avant de générer les épics → lire tous les fichiers de `adr-l2/`
- Avant de générer les features → lire tous les fichiers de `adr-l3/`

### 2. Ordre de lecture

Les ADR sont lus dans l'ordre numérique croissant au sein de chaque dossier. Les ADR numérotés 000 sont lus en premier (templates et méta-décisions).

### 3. Gestion des conflits

En cas de conflit entre deux ADR ou entre un ADR et une instruction, **ne pas générer**. Créer immédiatement un fichier `conflits/CONFLIT-[YYYY-MM-DD]-[slug].md` décrivant :
- Les deux règles en conflit (avec leurs références exactes)
- La décision ou l'artefact bloqué

La génération reste suspendue jusqu'à résolution par le chef d'orchestre. Ne pas tenter de résoudre le conflit seul.

### 4. Hiérarchie des niveaux

| Niveau | Dossier   | Artefact gouverné |
|--------|-----------|-------------------|
| L0     | `adr-l0/` | Règles de gouvernance des ADR eux-mêmes |
| L1     | `adr-l1/` | PRD |
| L2     | `adr-l2/` | Épics |
| L3     | `adr-l3/` | Features |

### 5. Exhaustivité

Tous les fichiers présents dans un dossier ADR au moment de la génération sont lus, y compris les ADR ajoutés après la création initiale du skill.
