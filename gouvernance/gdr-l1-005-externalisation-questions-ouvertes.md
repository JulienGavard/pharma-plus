# GDR-L1-005 — Externalisation des questions ouvertes

**Date** : 2026-06-09
**Statut** : Accepté

## Section modifiée
Structure du PRD — section « Questions ouvertes » prévue par le template ADR-L1-000.

## Avant
Le template PRD (ADR-L1-000) prévoit une section « Questions ouvertes » à l'intérieur de `docs/PRD.md`. Dans les faits, les questions ouvertes vivaient dans un fichier `questions-ouvertes.md` à la racine du dépôt, sans gouvernance explicite — un artefact orphelin et un doublon potentiel de la section du template.

## Après
Les questions ouvertes sont maintenues dans un fichier dédié et autonome : `docs/questions-ouvertes.md`. Elles ne figurent pas dans `docs/PRD.md`. Le PRD reste centré sur la vision stable (problème, utilisateur, objectif, critères, contraintes) ; les questions ouvertes — par nature mouvantes et destinées à être fermées au fil du temps — sont tenues séparément.

## Raison du changement
Mélanger une vision stable et une liste de questions évolutives dans un même fichier nuit à la lisibilité et brouille la frontière entre « ce qui est décidé » et « ce qui reste à trancher ». Un fichier séparé clarifie cette frontière et évite que chaque mise à jour des questions ouvertes ne touche le PRD. Le fichier est désormais explicitement gouverné — il n'est plus orphelin.

## Impact
- Le fichier `questions-ouvertes.md` (racine) est déplacé vers `docs/questions-ouvertes.md`
- La section « Questions ouvertes » du template ADR-L1-000 ne s'applique plus : elle est remplacée par ce fichier externe (cet ADR supersède cette portion du template, sans modifier ADR-L1-000 conformément à ADR-L0-003)
- Une question ouverte fermée doit, si elle débouche sur un changement de vision, faire l'objet d'un ADR-L1 dédié
