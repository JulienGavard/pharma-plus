# ADR-L3-001 — Un fichier par feature

**Date** : 2026-06-08
**Statut** : Accepté
**Supersède** : ADR-L2-002 — Un fichier par feature (placé par erreur en L2)

## Contexte

Jusqu'ici, toutes les features étaient regroupées dans un seul fichier `docs/features.md`. Cette approche pose les mêmes problèmes qu'un fichier épics unique : navigation difficile, mises à jour peu ciblées, et traçabilité limitée.

## Décision

Chaque feature est écrite dans son propre fichier, stocké dans un sous-dossier de `docs/features/` correspondant à son épic parent.

### Convention de nommage

```
docs/features/[slug-de-l-epic]/[id-feature]-[slug-de-la-feature].md
```

Exemple : `docs/features/inventaire-du-stock/1-1-scan-code-barres.md`

- Le dossier parent reprend le slug de l'épic (même convention que `docs/epics/`)
- L'identifiant de la feature (ex. `1.1`) est converti en `1-1` dans le nom de fichier
- Le slug de la feature est dérivé de son titre en minuscules, espaces remplacés par des tirets, accents supprimés

### Format du fichier

Le contenu de chaque fichier respecte le template défini dans `adr-l3/ADR-L3-000-template-feature.md`.

## Conséquences

- Le fichier `docs/features.md` n'est plus utilisé — la génération écrit dans `docs/features/`
- Le skill product-manager doit écrire un fichier par feature au lieu d'un fichier unique
- L'interdiction de lire les fichiers cibles avant régénération (voir `adr-l0/ADR-L0-001-regles-lecture-adr.md`) s'applique à l'ensemble du dossier `docs/features/`
