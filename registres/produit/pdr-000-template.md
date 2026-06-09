# PDR-000 — Template Product Decision Record

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

Le registre `produit/` a pour racine le **PRD** (`produit/PRD.md`), document fondateur de la vision. Autour de lui, les **PDR** (Product Decision Records) enregistrent chaque décision de **nature produit** : ce que Pharma Plus fait, sa vision, le contenu de son offre.

Un PDR se distingue d'un GDR (gouvernance — comment on fabrique les specs) et d'un ADR (architecture logicielle — comment l'app est construite). Voir `gouvernance/gdr-l0-004-taxonomie-registres-decision.md`. Une décision produit s'accompagne généralement d'une édition du PRD ; le PDR en est la trace et la justification.

## Template

Fichier : `produit/pdr-NNN-slug.md` (NNN à partir de 001)

```
# PDR-NNN — [Titre court]

**Date** : [YYYY-MM-DD]
**Statut** : Proposé / Accepté / Rejeté

## Décision
[Ce que le produit fera, formulé clairement]

## Contexte / Raison
[Pourquoi cette décision — besoin, opportunité, retour utilisateur]

## Impact PRD
[Éléments du PRD créés ou modifiés, référencés par leur identifiant stable
(OBJ, CS-N, CL-N, CT-N, CR-N, CB-N) — voir gdr-l1-006]

## Conséquences
[Effets sur les épics, features, ou autres décisions]
```

## Règles

- Un PDR ne formule jamais de recommandation médicale ou posologique.
- Une décision produit qui modifie le PRD est enregistrée en PDR (et non en GDR-L1), conformément au routage de gdr-l0-004.
- Le contenu décisionnel d'un PDR est immuable ; seules ses références croisées peuvent être mises à jour (gdr-l0-003 assoupli par gdr-l0-004).
