# GDR-L2-001 — Règles de génération des épics

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

La génération des épics par un LLM est non déterministe par nature : deux relances sur le même PRD peuvent produire des résultats différents. Pour garantir que le même PRD produise toujours les mêmes épics, des règles de dérivation explicites sont nécessaires.

## Règles

### 1. Table de dérivation obligatoire

Avant de générer les épics, construire une table de dérivation qui relie chaque section du PRD aux épics qu'elle justifie. Cette table est le contrat de génération.

```
| Section PRD                  | Épic(s) dérivé(s)         |
|------------------------------|---------------------------|
| Critère de succès : [X]      | [Nom de l'épic]           |
| Contrainte : [Y]             | [Nom de l'épic]           |
| ...                          | ...                       |
```

Si une section du PRD ne produit aucun épic, le noter explicitement dans la table.

### 2. Ancrage obligatoire dans le PRD

Un épic ne peut être créé que s'il est directement justifié par au moins une section du PRD. Tout épic sans source PRD identifiée est interdit.

### 3. Champ Source PRD obligatoire

Chaque épic doit porter un champ `Source PRD` référençant la ou les sections du PRD qui le justifient. Le format exact d'un épic est défini dans `adr-l2/ADR-L2-000-template-epic.md`.

## Conséquences

- La génération des épics est traçable et reproductible : même PRD → même table de dérivation → mêmes épics
- Tout nouvel épic doit faire l'objet d'une mise à jour de la table de dérivation
- Toute modification du PRD qui impacte la table de dérivation entraîne une révision des épics concernés
