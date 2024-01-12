# word_project
en Java, pas encore d'interface graphique (je test actuellement des trucs avec Swing, pas terrible mais pour avoir une idée, je vais bientôt voir pour les formations sur OpenClassroom (Maven et Sping Boot)).

Plusieurs trucs importants : 
- changer la variable "url" avec le bon chemin sur ton pc menant vers la base de donnée 
il y a dans : Print.java, Print_list_word.java, Learn_algo.java (1 fois à chaque fois).

- bien ajouter les 4 fichiers dans le dossier lib et dans l'IDE les mettre en tant que : "add to library" (dans le fichier lib ou dans un autre).
- aussi le package Data qui relie tous les fichiers dans "src" (sur IntelliJ).


Petites remarques : 
- quand cela affiche les différentes listes de mots, plusieurs choses: les 5 chapitres sont les trucs que j'ai eu en cours d'anglais (ça fait des trucs pour faire des tests donc pratique).
Il y a marqué aussi "sqlite_schema" et "sqlite_sequence", je me suis pas penché dessus mais ça semble être des trucs intéressants (mais ce ne sont pas des listes de mots).

- Dans Learn_algo.java, il y a "l'algorithme" qui va sélectionner les mots pour s'entrainer :
dans un premier temps dans la colonne "train" le nombre d'entrainements pour chaque mots, si entre le minimum et le maximum il y a un écart supérieur à 3, il met les mots dont c'est le cas (les plus petits).
Puis, dans la colonne "level", il prend les mots dont le niveau est le plus faible (la fonction "sort" renvoi un dictionnaire avec).
C'est pas dingue, je souhaite l'améliorer, notamment mettre un nombre maximum de mots venant du nombre d'entrainements (pas long à faire), faire en sorte que ça s'adapte quand la liste a moins de 10 mots
ou beaucoup plus que 10.
Et surtout l'optmiser et essayer de le rendre plus lisible parce que là c'est chiant à lire.

- par la suite généraliser, dans le sens où là c'est pour apprendre des mots en français et anglais ce qui est limitant, donc je vais essayer de faire en sorte de voir pour soit :
faire un truc qui est général ou demander quand la liste est crée, si c'est un truc de traduction (avec les langues, mais faut que je réfléchisse pour voir comment le stocker), ou autre (à voir).

voilà
