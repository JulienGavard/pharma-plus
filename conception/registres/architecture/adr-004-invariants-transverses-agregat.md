# ADR-004 — Les invariants transverses appartiennent à l'agrégat

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Après la dé-anémisation des entités (`adr-003`), il restait des règles portant sur **plusieurs objets à la fois** — couverture (PRD × épics × features), contrat de dérivation (table × épics), unicité (toute la collection), existence du parent, correspondance dossier ↔ slug d'épic. Elles vivaient dans des classes `Regle` externes. Ces invariants n'appartiennent à aucune entité isolée : ils appartiennent à **l'ensemble**.

## Décision

`Specifications` devient la **racine d'agrégat** de l'ensemble des spécifications (PRD, table, épics, features). Elle :

- **compose** la validation intrinsèque de ses membres (les `violations…` des entités, `adr-003`) ;
- **porte** les invariants transverses (couverture, dérivation, unicité, parenté, dossier) ;
- expose `verifierConformite(): RapportDeConformite` — le domaine *possède* la vérification.

La couche `Regle` (interface + classes de règles) est **supprimée** : entités (intrinsèque) et agrégat (transverse) portent désormais toute la logique de conformité.

L'orchestration exécutable est confiée à `application/Harnais` (ex-`Validateur`) : il charge les spécifications, leur demande leur conformité, rend le rapport, retourne le code de sortie. Il ne contient aucune logique métier — le « validateur » réel, c'est l'agrégat.

### Nuance « validateur »

L'agrégat ne refuse pas de se construire s'il est incohérent : on doit pouvoir représenter l'invalide pour le signaler. Il **rapporte** ses violations plutôt que d'imposer ses invariants à la construction.

## Conséquences

- `Specifications` enrichi ; suppression de `regles/` (interface + 6 classes) et de l'ancien `Validateur`.
- `application/Harnais` orchestre ; `Main` l'appelle.
- Les tests des invariants transverses vivent dans `SpecificationsTest` ; les tests intrinsèques restent sur `EpicTest`/`FeatureTest`.
- Comportement du harnais inchangé (mêmes 32 violations à vide).
