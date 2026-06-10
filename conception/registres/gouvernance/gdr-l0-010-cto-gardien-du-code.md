# GDR-L0-010 — Le CTO, gardien du code applicatif

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Le Gouverneur (`gdr-l0-005`, `gdr-l0-009`) veille sur l'**organisation, la démarche, la roadmap et la partie fonctionnelle** — c'est-à-dire les spécifications, dont les règles sont les GDR. Mais le **code applicatif** (le harnais aujourd'hui, l'application Pharma Plus demain) a ses propres règles — les **ADR** du registre architecture — et sa propre dette technique. Personne ne les garde.

## Décision

On introduit un second gardien : le **CTO**, gardien du **code applicatif** (`src/`).

### Répartition des rôles

| Gardien | Domaine surveillé | Règles de référence |
|---------|-------------------|---------------------|
| **Gouverneur** | Organisation, démarche, roadmap, fonctionnel (les specs) | **GDR** (registre gouvernance) |
| **CTO** | Code applicatif (`src/`) | **ADR** (registre architecture) |

### Pouvoirs du CTO

Comme le Gouverneur, le CTO a plein pouvoir pour :

1. **Vérifier** la conformité du code aux ADR (Kotlin ; POO + DDD : séparation des couches, langage du domaine, value objects immuables ; entités non anémiques ; invariants transverses sur l'agrégat) et sa **qualité** (build et tests verts, couverture, lisibilité, duplication, dette).
2. **Bloquer** : en cas d'infraction, journaliser un **conflit** dans `conflits/` (bloquant), à traiter par le chef d'orchestre.
3. **Suggérer** : consigner ses **remarques** non-bloquantes dans les questions ouvertes, sous une section « Remarques du CTO — code ».

Le CTO ne décide pas et ne refactore pas de lui-même : il constate, bloque ou suggère. Le chef d'orchestre tranche.

## Conséquences

- Nouveau skill **cto** (parallèle au skill gouverneur).
- Le fichier des questions ouvertes accueille une troisième portée — le **code** — aux côtés du produit (`gdr-l1-005`) et de l'organisation (`gdr-l0-009`).
- La carte de gouvernance liste le CTO parmi les rôles ; le périmètre du Gouverneur y est précisé (il ne couvre pas le code).
