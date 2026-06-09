# GDR-L0-002 — Interdiction de lecture du journal lors de la génération

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

`JOURNAL.md` retrace l'historique narratif des décisions passées, notamment les artefacts produits lors de sessions précédentes (nombre d'épics, de features, thèmes couverts). Lire ce fichier avant de générer les épics ou les features introduit un biais : le système risque d'être influencé par un résultat antérieur plutôt que de dériver purement depuis le PRD et les ADR.

Ce risque est analogue à celui documenté dans la règle d'isolation de `docs/epics.md` et `docs/features.md` — mais le journal est encore plus trompeur, car il est rédigé en langage naturel et mémorable.

## Règle

`JOURNAL.md` ne doit jamais être lu lors de la génération des épics ou des features.

La génération part exclusivement du PRD et des ADR. Le journal n'est ni une source de vérité ni une contrainte de génération — c'est un récit a posteriori, sans autorité sur le contenu produit.

## Conséquences

- La génération des épics et features reste indépendante de tout résultat précédent
- Le journal peut être lu librement en dehors des phases de génération (analyse, archivage, onboarding)
- Si une décision passée doit contraindre une génération future, elle doit être formalisée dans un ADR — pas dans le journal
