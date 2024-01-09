# word_project
en Java, pas encore d'interface graphique (j'ai tenté un truc avec Swing mais vraiment trop chiant et pas terrible, donc je vais voir)

plusieurs trucs importants : 
- changer la variable "url" avec le bon chemin sur ton pc menant vers la base de donnée 
il y a dans : Print.java, Print_list_word.java, Learn.java, Learn_algo.java (1 fois à chaque fois)

- bien ajouter les 4 fichiers dans lib et dans l'IDE les mettre en tant que : "add to library" (dans le fichier lib ou dans un autre)

- quand cela affiche les différentes listes de mots, plusieurs choses: les 5 chapitres sont les trucs que j'ai eu dans anglais (ça fait des trucs pour faire des tests donc pratique).
Il y a marqué aussi "sqlite_schema" et "sqlite_sequence", je me suis pas penché dessus mais ça semble être des trucs intéressants (mais ce ne sont pas des listes de mots)

- Dans Learn_algo.java, il y a "l'algorithme" qui va sélectionner les mots pour s'entrainer :
dans un premier temps dans la colonne "train" le nombre d'entrainements pour chaque mots, si entre le minimum et le maximum il y a un écart supérieur à 3, il met les mots dont c'est le cas.
Puis, dans la colonne "level", il prend les mots dont le niveau est le plus faible (la fonction "sort" renvoi un dictionnaire avec)
C'est pas dingue, je souhaite l'améliorer, notamment mettre un nombre maximum de mots venant du nombre d'entrainements (pas long à faire), faire en sorte que ça s'adapte quand la liste a moins de 10 mots
ou beaucoup plus que 10.
Et surtout l'optmiser et essayer de le rendre plus lisible parce que là c'est chiant à lire.

voilà
