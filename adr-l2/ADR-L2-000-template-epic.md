# ADR-L2-000 — Template épic

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

Les épics sont les grands axes de valeur utilisateur, dérivés du PRD conformément aux règles définies dans `adr-l2/ADR-L2-001-regles-generation-epics.md`. Ce document définit leur format canonique.

## Template

Fichier : `docs/epics.md`

```
# Épics — [Nom du produit]

## [Titre court]
**Valeur** : [Bénéfice utilisateur en une phrase]
**Scope** : [Ce que ça inclut / exclut]
**Priorité** : [Critique / Haute / Moyenne / Basse]
**Lot** : [Lot 1 / Lot 2 / ...]
**Source PRD** : [Section(s) du PRD qui justifient cet épic]
```

## Règles

- Un épic sans champ `Source PRD` renseigné est invalide
- La notion d'allotissement (Lot) appartient au niveau L2 et L3 — elle n'apparaît pas dans le PRD
- Tout nouvel épic nécessite une mise à jour de la table de dérivation (voir ADR-L2-001)
