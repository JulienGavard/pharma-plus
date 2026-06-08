# ADR-L1-000 — Template PRD

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

Le PRD est le document de référence produit. Il définit le problème, l'utilisateur cible, l'objectif, les critères de succès et les contraintes. Toute modification du PRD après sa création initiale doit faire l'objet d'un nouvel ADR-L1 (numéroté à partir de 001).

## Template

```
# PRD — [Nom du produit]

## Problème
[Le problème exact, avec la chaîne causale identifiée]

## Utilisateur cible
[Description précise de l'utilisateur, ses comportements, son contexte]

## Objectif du produit
[Ce qu'on veut accomplir]

## Critères de succès
[Comment on mesure que le produit a réussi — métriques observables]

## Hors scope
[Ce que le produit ne fait pas — aussi important que ce qu'il fait]

## Contraintes
[Techniques, légales, business]

## Hypothèses non validées
[Ce qu'on suppose mais qu'on n'a pas encore prouvé]

## Questions ouvertes

- [ ] [Hypothèse 1 à tester]
- [ ] [Hypothèse 2 à tester]
- [ ] [Décision technique à prendre]
```

## Règles

- Le PRD est écrit dans `docs/PRD.md`
- Toute modification après création initiale est interdite sans ADR-L1 correspondant (numéro ≥ 001)
- Le PRD ne contient pas de notion d'allotissement — celle-ci appartient aux niveaux L2 et L3
- Le PRD ne formule jamais de recommandation médicale ou posologique
