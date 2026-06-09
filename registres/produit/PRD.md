# PRD — Pharma Plus

> Les identifiants entre crochets (`[ID]`) sont stables et référençables par les épics et features (voir ADR-L1-006). Ils ne changent jamais une fois attribués.

## Problème

`[PROB]` Les foyers français accumulent des médicaments périmés dans leur armoire à pharmacie. La chaîne causale est la suivante :

1. Le médecin prescrit un traitement sans savoir ce que le patient possède déjà chez lui
2. Le patient va à la pharmacie avec l'ordonnance sans connaître son stock
3. La pharmacie délivre des boîtes complètes, souvent remboursées par la Sécurité Sociale
4. Les médicaments non utilisés ou partiellement utilisés s'accumulent et périment
5. Tous les 6 mois, le patient trie et jette

Le problème se crée au moment de la prescription et se concrétise à la pharmacie. Il se révèle lors du tri semestriel.

---

## Utilisateur cible

`[USR]`

**Profil** : Adulte français bénéficiaire de la Sécurité Sociale, ayant un médecin traitant et une armoire à pharmacie familiale.

**Comportements clés** :
- Consulte un médecin régulièrement, repart avec des ordonnances
- Va à la pharmacie avec l'ordonnance sans vérifier son stock à la maison
- Trie son armoire à pharmacie environ tous les 6 mois
- Ne regarde pas systématiquement les dates de péremption avant de prendre un médicament

**Moments de contact avec les médicaments** :
- En rentrant de la pharmacie avec une nouvelle boîte
- Au moment de prendre un comprimé

---

## Objectif du produit

`[OBJ]` Donner à l'utilisateur une visibilité permanente sur son stock de médicaments — ce qu'il a, en quelle quantité, et jusqu'à quand — afin d'éviter les achats inutiles, le gaspillage et les prises de médicaments périmés. L'application lui permet également d'estimer les économies réalisées sur son reste à charge grâce à l'optimisation de son stock.

## Critères de succès

- `[CS-1]` L'utilisateur sait en moins de 10 secondes s'il possède un médicament donné et s'il est encore valable
- `[CS-2]` L'utilisateur ne rachète plus un médicament qu'il possède déjà
- `[CS-3]` L'utilisateur reçoit une alerte avant qu'un médicament ne périsse
- `[CS-4]` Le stock est maintenu à jour sans friction : moins de 30 secondes pour ajouter ou consommer une boîte
- `[CS-5]` L'utilisateur peut consulter à tout moment les économies estimées générées par l'application sur son reste à charge

---

## Contraintes

### Légales

- `[CL-1]` Les données de santé sont des données sensibles soumises au **RGPD**. Leur traitement requiert un consentement explicite, une finalité déclarée et un droit à l'effacement effectif.
- `[CL-2]` Si les données sont stockées dans le cloud, l'hébergeur doit être certifié **HDS** (Hébergeur de Données de Santé) conformément à l'article L.1111-8 du Code de la santé publique.
- `[CL-3]` Le stockage local (offline-first) permet de s'affranchir de l'obligation HDS tant que les données ne quittent pas l'appareil de l'utilisateur.
- `[CL-4]` L'application ne doit en aucun cas formuler de recommandation médicale ou posologique. *(Interdiction transversale — non soumise à l'obligation de couverture, voir ADR-L1-006.)*

### Techniques

- `[CT-1]` Le scan de code-barres doit fonctionner hors ligne
- `[CT-2]` La base de données des médicaments français (CIP/ANSM) doit être intégrée pour la récupération automatique des informations, incluant le prix et le taux de remboursement Sécurité Sociale

### Remboursement

- `[CR-1]` L'utilisateur renseigne une fois son taux de remboursement mutuelle dans ses paramètres
- `[CR-2]` Le reste à charge est calculé automatiquement : prix − remboursement sécu − remboursement mutuelle

### Business

- `[CB-1]` L'application doit être aussi simple qu'une liste de courses — zéro friction à l'ajout
