# GDR-L1-007 — Le PRD n'évolue qu'en conséquence d'un PDR

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Le routage de `gdr-l0-004` indiquait « décision produit → PDR + édition du PRD », sans préciser la primauté. Le dialogue avec le Product Manager pouvait laisser croire qu'on édite le PRD directement. Or le PRD est l'artefact de vision : il doit rester la *conséquence* de décisions tracées, jamais le lieu où une décision naît en silence.

## Décision

**Toute modification ou ajout de contenu produit se matérialise d'abord par un PDR, et seulement ensuite par l'édition du PRD qui en découle.**

- Le **PDR** est le record primaire de la décision produit.
- L'**édition du PRD** (`produit/PRD.md`) est une conséquence mécanique du PDR, jamais un acte autonome.
- Le dialogue avec le Product Manager produit des PDR ; il ne modifie jamais le PRD sans PDR correspondant.

## Conséquences

- Le flux du Product Manager (skill) reflète cette primauté : dialogue → PDR → édition du PRD.
- Tout diff de contenu du PRD doit être traçable à un PDR. Une édition du PRD sans PDR est une infraction (que le Gouverneur peut signaler — voir `gdr-l0-005`).
- Précise le routage de `gdr-l0-004` sans le contredire.
