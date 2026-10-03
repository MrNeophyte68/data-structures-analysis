[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/SdtsMVQV)
------------------------------------------------------------------------

![](resources/logo_poly.png)
<td><h1>INF2010 - Structures de données et algorithmes</h1></td>


------------------------------------------------------------------------

Travail pratique \#3
====================

Arbre Binaire de recherche
=============================================================

Objectifs
---------
* Apprendre le fonctionnement d’un arbre binaire de recherche
* Comprendre le fonctionnement d’un arbre AVL
* Utiliser les concepts vus en cours dans des analyses de complexité
* Utiliser un SIAG pour apprendre de nouveaux concepts


Astuces
-------
Veuillez consulter la section **Astuces** du README du travail pratique 1 pour la configuration du projet.

Utilisation de ChatGPT ou autres SIAG
=====================================

L'utilisation des SIAG est uniquement permise lorsque explicitement énoncé.
Un emoji 🤖 accompagnera aussi ces sections.

⚠️ Tout autre utilisation de ChatGPT ou autres SIAG est strictement interdite ⚠️

------------------------------------------------------------------------
Partie 1 : Implémentation des arbres binaires de recherche BST et AVL
---------------
Un arbre AVL est un arbre binaire de recherche équilibré. Celui-ci oblige que la différence entre la hauteur gauche et 
la hauteur droite soit inférieure à 2. 
Si cette condition n’est pas respectée, il faut rééquilibrer l’arbre avec l’algorithme des arbres AVL. 
Dans le cas du retrait d’un élément, une démarche spécifique doit être utilisée.
L'implémentation de ces algorithmes est disponible dans les diapositives du cours et dans le chapitre 4 du livre de Mark Allen Weiss.

Pour la première partie, il vous est demandé de compléter les classes suivantes:
- `BinaryTree`: Fonctionnalités permettant de traverser un arbre de différentes manières
- `BinarySearchTree`: Implémente un arbre de recherche qui non-équilibré
- `AvlTree`: Arbre de recherche AVL (équilibré)

**ATTENTION : Une note de 0 sera automatiquement attribuée si vous utilisez un arbre binaire d’une librairie quelconque.**


------------------------------------------------------------------------

Partie 2 : Planificateur de tâches sécurisé
----------------

## Contexte du problème

Vous intervenez sur le moteur de gestion d’un système de tâches critiques. Les données sont organisées dans un **Arbre de Recherche Binaire (BST) auto-équilibré**.
Pour des raisons de sécurité, le coût de chaque tâche est initialement **chiffré**.
Vous pouvez utiliser un SIAG (🤖) et toutes autres ressources pour déterminer la solution.

## Problème

Vous devez implémenter les méthodes de la classe `TaskManager` en respectant les règles logiques suivantes.

### Préparation et Déchiffrement

Le champ `executionCost` de chaque nœud est chiffré.  
Chaque nœud possède cependant une `decryptionKey` lisible.

#### Règles de déchiffrement

- Le coût d’un nœud se déchiffre en utilisant la clé fournie par son **parent** (via l’opérateur `XOR`).
- La clé contenue dans un nœud est nécessaire pour déchiffrer le coût de **ses enfants directs**.
- La **racine** de l’arbre est déchiffrée avec la clé 0

### Génération des Rapports de Priorité

L’arbre est structuré selon l’identifiant de priorité `priorityId`.

Vous devez pouvoir extraire les tâches :

- Soit dans l’ordre de la **plus petite** à la **plus grande priorité**.
- Soit dans l’ordre de la **plus grande** à la **plus petite priorité**.

### Évaluation du Coût Final

Chaque nœud parent possède un opérateur mathématique :  
`+`, `-`, `*` (multiplication), `^` (puissance)

#### Règles de calcul

- **Nœud avec deux enfants** :  
  Son coût final est le résultat de l’opération appliquée aux **coûts finaux de ses deux enfants**.

- **Autres cas (feuille ou un seul enfant)** :  
  Le nœud conserve son propre coût (une fois celui-ci déchiffré).

#### Logique de calcul

Le coût d’une tâche parente dépend directement de la résolution préalable des valeurs de ses sous-arbres.  

### Indice pour la Partie 2
Réfléchissez à la manière dont vous pourriez utiliser les différentes méthodes de parcours d'arbre dans chacune des étapes du problème.

Rapport
====================

⚠️ Attention, malgré que les SIAG soient permis pour la partie 2, vous devez répondre aux questions
dans vos propres mots ⚠️

Fournissez un rapport répondant aux questions et réflexions ci-dessous sur la __partie 2__:

1. Expliquez le fonctionnement de votre système (logique de l'implémentation)
2. Faites l'analyse de la complexité temporelle et spatiale pour la génération de rapports de priorité et pour le calcul du coût total des tâches
3. Décrivez _brièvement_ votre utilisation des SIAG dans ce TP 
   - Questions et réponses obtenus, etc.
   - Avez-vous utilisez d'autres ressources?
   - Quelles conclusions ou observations avez-vous tirées de l'utilisation des SIAG pour ce TP?
    
## Astuces et exemple pour les analyses de complexité

- Consultez la section 2.4 du livre de Weiss pour les règles de bases et des exemples.
    - Pour l'analyse des relations de récurrence, voir la section 7.6.1 (Pas besoin pour ce TP)
- Faites des analyses complètes et montrez vos simplifications.

---
Soit le code suivant:

```java
int countOccurrences(String sentence, char letter) {
    int nOccurrences = 0;

    for (int i = 0; i < sentence.length(); i++) {
        if (sentence.charAt(i) == letter) {
            nOccurrences++;
        }
    }

    return nOccurrences;
}
```

Exemple de réponse attendue:

__Logique de la solution__:

La solution itère au travers de la chaine de caractère et compare chaque lettre.
Lorsqu'une lettre correspond à la lettre recherchée, un compteur est incrémenté.
Le compteur contient le nombre d'occurrences de la lettre recherché à la fin de l'itération.

### Analyses de complexités

Soit `n` la longueur de `sentence`

__Complexité temporelle__

- L'initialisation de `nOccurrences` se fait en O(1)
- La boucle s'exécute `n` fois
    - La comparaison dans la boucle se fait en O(1)
    - l'incrémentation de `nOccurrences` se fait en O(1)
    - Le corps de la boucle est donc O(1)
    - La boucle est donc O(n * (1+1)) = O(n)
- La fonction s'exécute donc en O(1 + n) = O(n)

__Complexité spatiale__

La fonction utilise les variables `nOccurences` et `i` qui sont de taille constante, la complexité spatiale
est donc de O(1+1) = O(1).

------------------------------------------------------------------------

Soumission
==========

### Git

À la date de la remise, votre repo git devra contenir votre code final.

### Moodle

Rapport en format PDF non compressé (pas de zip!)

------------------------------------------------------------------------

Barème de correction
--------------------
|          |                      |     |
|----------|----------------------|-----|
| Partie 1 | Réussite des tests   | /8  |
| Partie 2 | Réussite des tests   | /7  |
| Rapport  | Q1                   | /1  |
|          | Q2                   | /2  |
|          | Q3                   | /1  |
| Qualité  | Code & Rapport       | /1  |
| Total    |                      | /20 |


#### ATTENTION
* Si une fonction n’a pas la complexité correcte, vous perdrez les points pour tous les tests utilisant cette fonction.
* Un chargé vérifiera que votre code respecte les complexités spécifiées et ne contourne pas les tests avant d'attribuer les points pour la *Réussite des tests*.
* Les tests sont indicatifs de votre note, mais tout le code sera révisé pour vérifier qu'il répond aux exigences. La note finale peut différer de celle des tests.

------------------------------------------------------------------------

#### Qu'est-ce que du code de qualité?
* Absence de code dupliqué
* Absence de warnings à la compilation
* Absence de code mort: code en commentaire, variables inutilisées, etc.
* Respect des mêmes conventions de codage dans tout le code produit :
  * Langue utilisée
  * Noms des variables, fonctions et classes
* Variables, fonctions et classes avec des noms pertinents et clairs expliquant leur intention
* Code bien commenté et documenté pour expliquer les parties complexes
* Respect des principes de programmation orientée objet (si applicable): encapsulation, héritage, polymorphisme

Pour plus d'informations, consultez [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

--- 

Le dernier commit de votre répertoire sera utilisé comme remise finale. Chaque jour de retard créera une pénalité
additionnelle de 20 %. Aucun travail ne sera accepté après 4 jours de retard.

---

Bonne chance 🚀
