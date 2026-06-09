# GDR-L1-001 — Format des ADR de niveau 1 (changements PRD)

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

Toute modification du PRD après sa création initiale doit être documentée sous forme d'ADR-L1. Ce document définit le format à respecter pour ces enregistrements de changement.

## Format

Fichier : `adr-l1/ADR-L1-[NNN]-[slug].md` (NNN numéroté à partir de 001)

```
# ADR-L1-[NNN] — Modification PRD : [titre court]

**Date** : [YYYY-MM-DD]
**Statut** : Proposé / Accepté / Rejeté

## Section modifiée
[Quelle partie du PRD est touchée]

## Avant
[Contenu ou décision d'origine]

## Après
[Nouveau contenu ou nouvelle décision]

## Raison du changement
[Pourquoi le PRD évolue — nouvelle information, pivot, retour utilisateur…]

## Impact
[Sur les épics, features, contraintes, ou hypothèses existantes]
```

## Règles

- Un ADR-L1 est créé en même temps que la modification du PRD, jamais après
- Le PRD ne peut pas être modifié sans ADR-L1 correspondant
- ADR-L1-000 est réservé au template PRD ; ADR-L1-001 au format des changements — les changements réels démarrent à partir de ADR-L1-002
