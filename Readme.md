# Analyse des problèmes de Observer en asynchrone

Si pas de gestion de cohérence => bazar complet potentiel, les observers voient des choses différentes.

Solutions possibles :

1. Resynchroniser comme dans la version séquentielle : cohérence atomique

    - plus : on est en terrain connu concernant la gestion des données

    - moins : on gâche totalement les possibilités de parallélisme

    - moins : pas toujours réalisable

2. Relâcher la cohérence : différentes concessions à la "consistency"

## Trois modèles de cohérence

Problème des lecteurs rédacteurs...
Ici on a pour Subject une variable qui est l'attribut valeur du sujet, 1 rédacteur (le mutateur), et N lecteurs (les observers).
Définissons les cohérences dans ce cadre.
Soit V la suite des valeurs écrites dans la variable V = V_1, V_2,...,V_x produites par le mutateur (= rédacteur).
Soit L(i) la suite des valeurs lues par chaque observer O_i : L(i) = L(i)_1,...

### Cohérence atomique

#### Définition de atomique

Pour tout i : L(i) = V

#### Réalisation (comment faire)

Alternance rédacteurs/lecteurs (exclusion mutuelle entre écriture et lecture collective) :

1. Initialement le rédacteur peut écrire dans la variable (= le mutateur peut modifier le sujet,
un appel à setValue(v_i))

1. À la fin de l'écriture, la phase de lecture commence.

1. Pendant la phase de lecture, toute écriture est interdite.

1. Lorsque tous les lecteurs ont lu une fois (par appel à getValue()) la phase de lecture termine
et la phase d'écriture commence.

### Cohérence séquentielle

#### Définition de séquentielle

1. Pour tout i : L(i) est une sous-suite de V

1. Pour tout i : L(i) = L ; une seule sous-suite autorisée => tous les lecteurs (Observers) voient la même chose

Avantage par rapport à la cohérence atomique : on ne bloque pas le mutateur (pas d'exclusion mutuelle entre
lecteurs et rédacteur).

Inconvénient : on peut perdre des valeurs produites par le rédacteur (mutateur).

#### Réalisation

1. Les lecteurs lisent une copie de la variable.

1. Le mutateur écrit dans l'original, sans exclusion mutuelle.

1. Au début d'une phase de lecture, on copie l'original dans la variable copie et les getValue() retourne la valeur de la copie.

1. Lorsque tout les lecteurs ont lu une fois, la phase de lecture est finie : pendant la phase de lecture la copie est "gelée".

En d'autres termes : une variable pour le rédacteur, une autre pour les lecteurs ; cohérence atomique de la copie

### Incohérence (assistée)

#### Définition de la cohérence causale (par époques)

1. Pour tout i : L(i) est une sous-suite de V

#### Implémentation de causale

1. On utilise un mécanisme d'horloge (estampille).

1. Au moment de l'écriture de la valeur V_i, la valeur est estampillée avec i.

1. Lors d'une lecture, un lecteur reçoit la valeur courante de la variable, avec son estampille : (V_i,i).

1. Si la valeur reçue est plus ancienne que celle précédemment reçue, on ne garde que la plus récente (au sens des estampille, = celle qui
a l'estampille la plus élevée).


## Oracles

### Oracle atomique

L'oracle récupère la liste des valeurs lues par chaque afficheur :

A1 = 1, 2, 3
A2 = 1, 2, 3, 4
A3 = 1, 2, 3, 4
A4 = 1, 2, 3, 4, 5
 => atomique ok

A1 = 1,  3
A2 = 1,  3, 4
A3 = 1,  3, 4
A4 = 1,  3, 4, 5
 => atomique nok, séquentiel => ok

A1 = 1,  3, 5
A2 = 1,  3, 4
A3 = 1,  2, 5
A4 = 1,  2, 3
 => atomique nok, séquentiel => nok, époque => ok

A1 = 1,  3, 5
A2 = 1,  5, 4
A3 = 1,  2, 5
A4 = 1,  2, 3
 => atomique nok, séquentiel => nok, époque => nok
