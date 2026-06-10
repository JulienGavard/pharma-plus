# GDR-L0-009 — Le Gouverneur critique l'organisation

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Le Gouverneur (`gdr-l0-005`) vérifie la conformité aux règles GDR et bloque les infractions. Mais une organisation peut être parfaitement conforme et pourtant perfectible : redondances, frontières floues, dette, incohérences entre records, ergonomie du flux. Rien ne capte ces signaux faibles, qui ne sont pas des violations de règles.

## Décision

Le Gouverneur acquiert une troisième mission : **porter un regard critique sur l'ensemble de l'organisation** du dépôt et consigner ses remarques.

### Distinction conflit / remarque

| | Nature | Destination | Caractère |
|--|--------|-------------|-----------|
| **Conflit** (`gdr-l0-005`) | Infraction à une règle GDR | `conflits/` | Bloquant |
| **Remarque** (présent record) | Critique, amélioration, question d'organisation | `questions-ouvertes` | Non bloquant |

### Fonctionnement

- Après la vérification de conformité, le Gouverneur prend du recul et évalue la cohérence, la lisibilité, les redondances, les frontières et la dette de l'organisation.
- S'il a des remarques, il les ajoute dans le fichier des **questions ouvertes** (`$QUESTIONS_OUVERTES`), sous une section dédiée **« Remarques du Gouverneur — organisation »**.
- Il n'ajoute que des remarques **nouvelles** (pas de doublon) ; s'il n'a rien à signaler, il ne touche pas au fichier.
- Une remarque est une **proposition**, jamais une action : le Gouverneur ne réorganise rien ; c'est au chef d'orchestre de trancher.

### Portée du fichier des questions ouvertes

Le fichier des questions ouvertes — jusqu'ici réservé aux questions **produit** (`gdr-l1-005`) — accueille désormais aussi les remarques **d'organisation** du Gouverneur, dans sa propre section. Les deux usages cohabitent sans se mélanger.

## Conséquences

- Le skill Gouverneur gagne une partie « Critique de l'organisation ».
- Le Gouverneur reste sans autorité d'action : il constate, bloque (conflits) ou suggère (remarques), mais ne décide pas.
