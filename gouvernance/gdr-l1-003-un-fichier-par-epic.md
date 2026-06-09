# ADR-L1-003 — Un fichier par épic

**Date** : 2026-06-08
**Statut** : Accepté

## Contexte

Jusqu'ici, tous les épics étaient regroupés dans un seul fichier `docs/epics.md`. Cette approche rend difficile la navigation, la mise à jour ciblée d'un épic, et la traçabilité des modifications épic par épic.

## Décision

Chaque épic est désormais écrit dans son propre fichier, stocké dans le dossier `docs/epics/`.

### Convention de nommage

```
docs/epics/[slug-de-l-epic].md
```

Exemple : `docs/epics/inventaire-du-stock.md`

Le slug est dérivé du titre de l'épic en minuscules, avec les espaces remplacés par des tirets et les accents supprimés.

### Format du fichier

Le contenu de chaque fichier respecte le template défini dans `adr-l2/ADR-L2-000-template-epic.md`.

## Conséquences

- Le fichier `docs/epics.md` n'est plus utilisé — la génération écrit dans `docs/epics/`
- Le skill product-manager doit écrire un fichier par épic au lieu d'un fichier unique
- L'interdiction de lire les fichiers cibles avant régénération (voir `adr-l0/ADR-L0-001-regles-lecture-adr.md`) s'applique à l'ensemble du dossier `docs/epics/`
