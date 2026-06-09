# GDR-L0-005 — Le Gouverneur, gardien de la conformité GDR

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Le skill Gouverneur a d'abord été introduit pour générer `docs/gouvernance.md`. Mais sa vraie valeur est ailleurs : puisqu'il connaît l'ensemble des règles GDR, il est le mieux placé pour **vérifier qu'aucune n'est enfreinte**. Sans ce rôle, les règles restent des consignes qu'on espère respectées.

## Décision

Le rôle **premier** du Gouverneur est de **vérifier qu'aucune règle GDR n'est enfreinte** — par l'utilisateur, par le Product Manager, ou par Claude.

- Il lit l'intégralité des GDR (`gouvernance/gdr-*.md`).
- Il confronte l'état courant du dépôt aux règles : structure des registres, nommage (slug, préfixes), frontmatter, immuabilité du contenu décisionnel, couverture, contrat de dérivation, routage par nature.

### En cas d'infraction

Le Gouverneur ne corrige pas silencieusement et ne laisse pas passer. Il :

1. **Stoppe la modification** fautive (elle n'est pas appliquée / pas livrée).
2. **Journalise un conflit** dans `conflits/CONFLIT-[YYYY-MM-DD]-[slug].md`, décrivant la règle GDR violée (référence exacte), la modification bloquée, et l'auteur (utilisateur / Product Manager / Claude).

Le conflit reste ouvert jusqu'à résolution par le **chef d'orchestre**. Le Gouverneur n'a pas autorité pour trancher seul — il constate, bloque et journalise. Ce mécanisme prolonge la gestion des conflits définie par `gdr-l0-001`.

### Sous-produit

Le Gouverneur maintient la carte `docs/gouvernance.md` (il connaît déjà toutes les règles ; la carte en est le reflet lisible).

## Conséquences

- Le skill Gouverneur est mis à jour : vérification de conformité → blocage + journal de conflit en cas d'infraction → puis régénération de la carte.
- Les règles de **sortie** (testables sur l'artefact) seront à terme automatisées par le harnais `validate.py` ; le Gouverneur couvre l'ensemble, y compris ce qui n'est pas encore automatisé.
