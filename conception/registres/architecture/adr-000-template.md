# ADR-000 — Template Architecture Decision Record

**Date** : 2026-06-09
**Statut** : Accepté (template)

## Contexte

Le registre `architecture/` accueille les **ADR** (Architecture Decision Records) au sens canonique : les décisions techniques de réalisation du logiciel Pharma Plus (stockage, scan hors-ligne, modèle de données, frameworks, synchronisation…).

Ce registre est **réservé** : il ne contient que ce template jusqu'au démarrage du développement. Il se distingue du registre de gouvernance (GDR — comment on fabrique les specs) et du registre produit (PDR — ce que le produit fait). Voir `gouvernance/gdr-l0-004-taxonomie-registres-decision.md`.

## Template

Fichier : `architecture/adr-NNN-slug.md` (NNN à partir de 001)

```
# ADR-NNN — [Titre court]

**Date** : [YYYY-MM-DD]
**Statut** : Proposé / Accepté / Rejeté / Remplacé

## Contexte
[Le problème technique à résoudre, les forces en présence]

## Décision
[La décision d'architecture retenue]

## Alternatives considérées
[Les options écartées et pourquoi]

## Conséquences
[Effets positifs et négatifs, dette éventuelle, features impactées]
```

## Règles

- Un ADR documente une décision **technique** ; il ne redéfinit ni la vision produit (PDR) ni les règles de fabrication des specs (GDR).
- Un ADR peut référencer les features qu'il met en œuvre (par leur `id`).
- Le contenu décisionnel d'un ADR est immuable ; seules ses références croisées peuvent être mises à jour (gdr-l0-003 assoupli par gdr-l0-004).
