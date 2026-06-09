# GDR-L0-006 — Externalisation de la roadmap

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

La roadmap du harnais était embarquée dans `docs/gouvernance.md` (section 7). Or `gouvernance.md` décrit des **principes stables**, tandis que la roadmap est par nature **mouvante** : son état change à chaque avancement. Les mélanger oblige à régénérer toute la carte à chaque progrès, et brouille la frontière entre « ce qui est stable » et « ce qui évolue » — le même problème que celui résolu pour les questions ouvertes (`gdr-l1-005`).

## Décision

La roadmap est externalisée dans son propre fichier : `docs/roadmap.md`.

- `docs/gouvernance.md` n'en conserve qu'un **pointeur** (un renvoi vers `docs/roadmap.md`), pas le détail des étapes.
- La roadmap liste les étapes, leur état (✅ / ⏳) et les objectifs (ex. les catégories de tests visées).

## Conséquences

- Création de `docs/roadmap.md`.
- Le skill Gouverneur référence la roadmap externe au lieu de l'embarquer dans la carte.
- La roadmap peut évoluer (cocher une étape) sans déclencher une régénération de `gouvernance.md`.
