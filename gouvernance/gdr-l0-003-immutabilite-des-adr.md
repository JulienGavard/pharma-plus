# GDR-L0-003 — Immutabilité des ADR

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

Un ADR est un enregistrement de décision — il documente ce qui a été décidé, pourquoi, et à quel moment. Modifier un ADR existant efface cette trace et rend l'historique des décisions illisible. C'est contraire à la finalité même du système.

## Règle

**Il est interdit de modifier un ADR existant, quel que soit son niveau (L0, L1, L2, L3).**

Toute évolution d'une règle ou d'une décision existante donne lieu à la création d'un nouvel ADR, numéroté dans la continuité du dossier concerné. Le nouvel ADR peut référencer l'ADR qu'il remplace ou complète, en indiquant explicitement la relation (supersède, complète, annule).

## Format recommandé pour un ADR de remplacement

```
**Supersède** : ADR-LX-YYY — [Titre de l'ADR remplacé]
```

## Conséquences

- L'historique des décisions est toujours lisible et traçable
- Une décision annulée reste visible dans le dossier avec son contexte d'origine
- Le numéro croissant des ADR reflète l'ordre chronologique des décisions
- Cette règle s'applique à elle-même : ADR-L0-003 ne peut pas être modifié
