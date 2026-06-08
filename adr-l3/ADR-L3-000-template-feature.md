# ADR-L3-000 — Template feature

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

Les features sont les capacités précises du produit, dérivées des épics. Une feature est testable et livrable indépendamment. Ce document définit leur format canonique.

## Template

Fichier : `docs/features.md`

```
# Features — [Nom du produit]

## Epic : [Titre de l'épic]

### [Feature X.Y — Titre]
**Description** : [Ce que fait la feature]
**Critère d'acceptation** : [Comment on sait que c'est terminé]
**Priorité** : [Critique / Haute / Moyenne / Basse]
**Lot** : [Lot 1 / Lot 2 / ...]
**Source PRD** : [Section ou critère du PRD que cette feature implémente]
```

## Règles

- Une feature est toujours rattachée à un épic existant
- Une feature sans champ `Source PRD` renseigné est invalide
- Les features d'un même épic peuvent appartenir à des lots différents
- Une feature n'exprime jamais de recommandation médicale ou posologique
