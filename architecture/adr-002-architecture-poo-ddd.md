# ADR-002 — Architecture du code : POO et Domain-Driven Design

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Au moment d'écrire le premier code (le harnais), nous voulons un code dont la logique métier est explicite, lisible et durable — pas une plomberie technique où les règles du domaine se perdent. Deux approches plus techniques ont été écartées en chemin : un script procédural unique, et l'externalisation des règles en schémas JSON ; toutes deux trop éloignées du langage métier.

## Décision

Tout le code suit le paradigme **POO + Domain-Driven Design**.

### Langage du domaine (ubiquitous language)

Les objets métier sont nommés dans **le langage du domaine, en français** : `Slug`, `IdentifiantPrd`, `Epic`, `Feature`, `TableDeDerivation`, `Violation`, `RapportDeConformite`, `RegleDeCouverture`… Le code parle la même langue que les records de gouvernance et le PRD.

### Séparation des couches

- **Domaine** — entités, value objects et règles métier. Aucune entrée/sortie : il ne connaît ni fichiers ni formats.
- **Infrastructure** — logique technique : lecture du système de fichiers, parsing (frontmatter, Markdown), rendu (console). Seul endroit qui sait ce qu'est un fichier `.md`.
- **Application** — orchestration, sans logique métier.

### Organisation des dossiers

Tout le code vit sous **`src/`**, avec **un sous-dossier par sous-domaine** au sens DDD. Le premier sous-domaine est **`harnais`** (validation de conformité des artefacts). En Kotlin/Gradle (`architecture/adr-001`), cela se concrétise par des packages sous `src/main/kotlin/…/<sous-domaine>/{domaine, infrastructure, application}`, et les tests sous `src/test/kotlin/`.

### Style POO

Value objects immuables quand c'est pertinent ; règles métier exprimées comme des objets (une classe par règle, p. ex. `RegleDeStructure`, `RegleDeCouverture`).

## Conséquences

- Le harnais est (ré)implémenté selon cette architecture en trois couches.
- Cet ADR est la source d'autorité de ces conventions ; la note de mémoire de travail qui les portait jusqu'ici n'en est qu'un rappel.
- Toute nouvelle brique de code crée, si besoin, son propre sous-domaine sous `src/`.
