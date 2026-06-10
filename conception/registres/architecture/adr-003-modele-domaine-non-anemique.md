# ADR-003 — Modèle de domaine non anémique

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

La première version du harnais séparait d'un côté les entités (`Epic`, `Feature`) — de simples *data class* sans comportement — et de l'autre des règles externes qui portaient toute la logique les concernant. C'est le symptôme de l'**Anemic Domain Model** (M. Fowler) : un `Epic` ne sait pas dire s'il est bien formé.

## Décision

Le domaine n'est pas anémique : **une entité porte sa propre validation**.

- `Epic` et `Feature` exposent leurs propres violations *intrinsèques* : `violationsStructurelles()`, `violationsDeNommage()`, `violationsDeReference(prd)`. La logique « d'un épic sur lui-même » vit sur l'épic.
- Les règles de structure, nommage et référence deviennent de **minces orchestrateurs** qui délèguent à l'entité (`specs.epics.flatMap { it.violationsStructurelles() }`).
- Les **tests** vérifient le comportement *sur les entités* (`EpicTest`, `FeatureTest`).

## Conséquences

- `Epic` et `Feature` gagnent leurs méthodes de validation ; les règles correspondantes s'amincissent.
- Les tests unitaires de structure, nommage et référence relatifs à un épic ou une feature migrent vers les tests d'entités.
