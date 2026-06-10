# Roadmap du harnais — Pharma Plus

> Externalisée de `docs/gouvernance.md` (voir `gouvernance/gdr-l0-006`). Document **mouvant** : son état évolue à chaque avancement, indépendamment de la carte de gouvernance qui, elle, décrit des principes stables.

L'objectif de déterminisme se construit en étapes, chacune cadrée par ses propres records.

| Étape | Contenu | État |
|-------|---------|------|
| **1. Nettoyer les bloquants** | PRD sous registre, questions-ouvertes gouvernées, numéro d'épic fixé | ✅ Fait |
| **2. ID + frontmatter** | Identifiants stables du PRD, slug pur, frontmatter YAML des épics/features | ✅ Fait |
| **Réorganisation par nature** | Trois registres (gouvernance/produit/architecture), migration des records | ✅ Fait |
| **3. Le validateur** | Un schéma par artefact + un script `validate.py` qui lève les violations | ⏳ À venir |
| **4. Brancher la boucle** | Intégrer `validate.py` au flux du Product Manager, lier chaque GDR-de-sortie à son test | ⏳ À venir |

## Autres chantiers

- **Index par registre** ⏳ — Dans chaque registre (`gouvernance/`, `produit/`, `architecture/`), ajouter un fichier d'index contenant un **tableau résumé des records** : au minimum l'**identifiant**, le **statut**, le **nom** du record, et les **références croisées** éventuelles (records supersédés, annulés, complétés).

## Les catégories de tests visées

- **Structurel** — frontmatter présente, champs obligatoires non vides, énumérations valides.
- **Référentiel** — tout `source_prd` existe dans le PRD ; tout épic parent existe.
- **Couverture** — chaque élément couvrable du PRD est porté par ≥ 1 épic et ≥ 1 feature ; les interdictions transversales (ex. CL-4) en sont exclues.
- **Contrat de dérivation** — bijection entre la table et les fichiers générés.
- **Nommage** — `slug == slugify(titre) == nom de fichier`, numérotation cohérente.
- **Unicité** — pas deux artefacts partageant le même `id` ou `slug`.
