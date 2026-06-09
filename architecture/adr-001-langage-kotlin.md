# ADR-001 — Kotlin comme langage du projet

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Premier code du projet : le harnais de validation. Au moment de l'écrire, le langage avait été choisi par défaut (Python), sans décision explicite — un script `validate.py` né d'une formulation de la roadmap, jamais tranché comme une vraie décision d'architecture. Or le choix du langage est une décision d'architecture logicielle (nature n°3, voir `gouvernance/gdr-l0-004`) : il a sa place ici, dans le registre `architecture/`, qu'il inaugure.

Le PRD décrit une application **mobile offline-first** (scan de code-barres hors ligne, stockage local sur l'appareil — `produit/PRD.md`, `CT-1`, `CL-3`). Plutôt que d'avoir un harnais dans un langage et une app dans un autre, nous voulons **un stack unifié**.

## Décision

**Kotlin est le langage de l'ensemble du projet** — l'application Pharma Plus comme l'outillage (dont le harnais).

- Kotlin est un choix naturel pour une app mobile offline-first (Android natif, ou Kotlin Multiplatform si un partage de code devient utile).
- Le harnais est écrit en Kotlin lui aussi, pour rester sur le même stack que l'app.

## Alternatives considérées

- **Python** (le défaut initial) : excellent pour un petit outil de traitement de texte, mais il aurait fait du harnais un stack séparé de l'app — écarté au profit de la cohérence.
- **TypeScript / React Native** : viable pour du mobile multiplateforme, mais l'utilisateur a tranché pour Kotlin.

## Conséquences

- Le prototype procédural `validate.py` (Python) est abandonné ; le harnais est ré-implémenté en Kotlin.
- Outillage : projet Kotlin (build Gradle / JVM) ; le harnais sera un exécutable en ligne de commande.
- L'architecture interne du code suit les conventions définies par `architecture/adr-002`.
- Le choix de la plateforme mobile précise (Android natif vs Kotlin Multiplatform) fera l'objet d'un ADR ultérieur, le moment venu.
