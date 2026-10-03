[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ttQdivmg)
------------------------------------------------------------------------

![](resources/logo_poly.png)
<td><h1>INF2010 - Structures de données et algorithmes</h1></td>

Merci au cours INF3500 pour le format du Markdown

------------------------------------------------------------------------

Travail pratique \#2
====================

Tables de hachage
=============================================================

Objectifs
---------

* Apprendre le fonctionnement d’une table de hachage
* Comprendre la complexité asymptotique d’une table de hachage
* Utiliser une table de hachage dans un problème complexe

Astuces
--------------------------

Veuillez consulter la section **Astuces** du README du travail pratique 1 pour la configuration du projet.

Utilisation de ChatGPT ou autres SIAG
=====================================

L'utilisation des SIAG est uniquement permise lorsque explicitement énoncé.
Un emoji 🤖 accompagnera aussi ces sections.

⚠️ Tout autre utilisation de ChatGPT ou autres SIAG est strictement interdite ⚠️

Partie 1: Implémentation d'une table de hachage
===============================================

### Objectif

Implémenter une table de hachage gérant les collisions à l’aide de listes chaînées et réaliser les tests nécessaires
pour valider son fonctionnement.

### Instructions

1. Implémentez la table de hachage dans le fichier `Part1.HashMap.java`.
2. Utilisez des listes chaînées chaînées de la classe `Node` pour gérer les collisions.
3. Commencez par implémenter les méthodes `get()` et `put()`, car les tests des autres méthodes utilisent ces fonctions
   pour valider leur fonctionnement.
4. Respectez les conventions de codage et évitez d'utiliser des implémentations de tables de hachage de librairies
   externes.

**ATTENTION**: Une note de 0 sera attribuée si une table de hachage provenant d’une librairie quelconque est utilisée.

### Notes

- Le test `testComplexityWithBarometerOperation` peut échouer si des appels inutiles à `equals()` sont faits pour
  comparer les clés.

---

Partie 2: Problème typique d'entrevue
====================

### Problème

Étant donné une liste de nombre `nums` et un chiffre cible `k`, 
déterminez une paire de nombres `(a, b)` 
telle que leur somme donne le chiffre cible `a + b = k`.

### Exemple 

Entrées: `nums = [1, 3, 2, 4]` et `k = 5`

Sorties possibles: `(2, 3)`, `(3, 2)`, `(1, 4)` ou `(4, 1)`

---

### Instructions

1. **Implémentation initiale:**
    - Implémentez votre solution initiale sans aide externe dans `SolutionInitiale.java`.

2. Optimisation de la complexité temporelle (🤖)
    - Implémentez une solution avec une complexité temporelle optimale dans `SolutionOptimaleTemps.java`.
    - Vous pouvez utiliser un SIAG pour vous aider.

3. Optimisation de la complexité spatiale (🤖)
    - Implémentez une solution avec une complexité spatiale optimale dans `SolutionOptimaleEspace.java`.
    - Vous pouvez utiliser un SIAG pour vous aider.

---

Rapport
====================

Fournissez un rapport répondant aux questions et réflexions ci-dessous concernant la partie 2 du laboratoire:

1. Décrivez la logique de votre solution optimale en temps et analysez la complexité temporelle et spatiale.
2. Décrivez la logique de votre solution optimale en espace et analysez la complexité temporelle et spatiale.
3. Pouvez-vous être certains que vos solutions optimales le sont vraiment? justifiez.
4. Décrivez _brièvement_ votre utilisation des SIAG dans ce TP (questions et réponses obtenus, etc.).
    - Quelles conclusions ou observations avez-vous tirées de l'utilisation de ChatGPT pour ce TP?

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

À la date de la remise, votre repo git devra contenir votre code final

### Moodle

Remettre le rapport sur moodle

Rapport en format PDF non compressé (pas de zip!)

------------------------------------------------------------------------

Barème de correction
====================

|          |                    |     |
|----------|--------------------|-----|
| Partie 1 | Réussite des tests | /7  |
| Partie 2 | Réussite des tests | /6  |
| Rapport  | Q1                 | /2  |
|          | Q2                 | /2  |
|          | Q3                 | /1  |
|          | Q4                 | /1  |
| Qualité  | Code & Rapport     | /1  |
| Total    |                    | /20 |

#### ATTENTION

* Pour la Partie 1, si une fonction n’a pas la complexité correcte, vous perdrez les points pour tous les tests
  utilisant cette fonction.
* Un chargé vérifiera que votre code respecte les complexités spécifiées et ne contourne pas les tests avant d'attribuer
  les points pour la *Réussite des tests*.
* Les tests sont indicatifs de votre note, mais tout le code sera révisé pour vérifier qu'il répond aux exigences. La
  note finale peut différer de celle des tests.

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
