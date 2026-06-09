# GDR-L0-004 — Taxonomie des registres de décision et migration

**Date** : 2026-06-09
**Statut** : Accepté

## Contexte

Le terme « ADR » était surchargé : il désignait indistinctement trois natures de décisions très différentes.

1. **Gouvernance** — comment on fabrique les specs (immuabilité, slug, frontmatter, ordre de lecture). Décrit *l'usine*.
2. **Produit / vision** — ce que Pharma Plus doit faire (ex. l'estimation des économies). Décrit *le quoi*.
3. **Architecture logicielle** — comment Pharma Plus est techniquement construit. Décrit *le comment*, et n'avait aucun foyer.

La hiérarchie `L0→L3` était organisée par *artefact touché*, pas par *nature* : un même dossier `adr-l1/` mélangeait décisions produit (économies) et gouvernance (identifiants du PRD). Le mot canonique « ADR » (*Architecture Decision Record*) désigne pourtant la seule nature n°3, qui n'existait pas encore. Le projet étant jeune et petit (~20 records), c'est le moment le moins coûteux pour séparer proprement.

## Décision

### 1. Trois registres distincts par nature

| Registre | Dossier | Type de record | Niveaux | Format de fichier |
|----------|---------|----------------|---------|-------------------|
| **Gouvernance** | `gouvernance/` | **GDR** (Governance Decision Record) | Oui (L0–L3) | `gdr-lX-NNN-slug.md` |
| **Produit** | `produit/` | **PDR** (Product Decision Record) | Non | `pdr-NNN-slug.md` |
| **Architecture logicielle** | `architecture/` | **ADR** (Architecture Decision Record) | Non | `adr-NNN-slug.md` |

Le préfixe est en minuscules dans le nom de fichier (`gdr-`, `pdr-`, `adr-`) ; le titre interne du record garde la casse (`# GDR-L0-004 — …`). Les niveaux du registre de gouvernance sont conservés : `L0` gouverne les records eux-mêmes, `L1` le PRD, `L2` les épics, `L3` les features. Le terme « ADR » est désormais **réservé** au registre `architecture/` (sens canonique).

### 2. Règle de routage

| La décision change… | Registre |
|---------------------|----------|
| la forme, la structure ou le processus d'un artefact de spec | **GDR**, au niveau concerné (L0–L3) |
| le contenu ou la vision du produit (ce que fait Pharma Plus) | **PDR** + édition du PRD |
| l'architecture ou la technique du logiciel | **ADR** |

Ce routage **supersède** celui de l'ancien ADR-L1-001 (« tout changement du PRD → ADR-L1 ») pour les changements de nature produit, qui vont désormais en PDR. La règle de forme reste : un changement de structure du PRD reste un GDR-L1.

### 3. Assouplissement de l'immuabilité aux références croisées

GDR-L0-003 (ex-ADR-L0-003, immuabilité) est **assoupli par le présent record** : les **références croisées** d'un record (citations d'autres records, chemins de fichiers) **peuvent désormais être modifiées**. Le **contenu décisionnel** — contexte, décision, conséquences — reste, lui, strictement immuable. Cet assouplissement est ce qui rend la migration ci-dessous possible sans perte de cohérence.

### 4. Migration sanctionnée des records existants

Tous les records `adr-l0..l3/ADR-L*` sont migrés vers `gouvernance/gdr-l*` (renommage du fichier, du titre interne et des références croisées), **sauf `ADR-L1-002`** (économies, nature produit) relocalisé en `produit/pdr-001`. Le numéro `GDR-L1-002` n'est pas réattribué (trou assumé).

Cette migration relocalise et renomme sans rien effacer : la sémantique de chaque décision est strictement préservée, l'historique reste traçable via git. Conformément à la section 3, les références croisées sont alignées vers les nouveaux noms ; l'équivalence `ADR-LX-NNN ≡ GDR-LX-NNN` reste vraie pour toute citation historique non encore alignée.

## Conséquences

- Création de `gouvernance/`, `produit/`, `architecture/`
- `architecture/` est réservé (template seul) jusqu'au démarrage du développement
- `ADR-L1-002` → `produit/pdr-001` ; le PRD reste inchangé (son contenu économies est désormais justifié par PDR-001)
- `docs/gouvernance.md`, `docs/table-de-derivation.md` et le skill product-manager sont mis à jour
- La distinction *sortie / processus* (testabilité) reste valable : elle est orthogonale à l'axe *nature*
