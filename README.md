# Application Studio Ghilbi


## Description du projet

Projet de développement mobile de 3ème année à l'ESIEA, utilisant le pattern MVC pour une application Android codée en Java.

L'application StudioGhilbi traite des données reçues au format JSON récupérées via requêtes HTTP GET sécurisées depuis l'API REST
de [ghibliapi.herokuapp.com](https://ghibliapi.herokuapp.com/) relative aux données de tous les films réalisés par le grand studio des films d'animation japonais Studio-Ghilbi. les détails de chaque film, comme l'année de sortie de film, le nom de réalisateur et producteur, ainsi que la note de critique de chaque film.


![](Image_readme/lego.PNG)
lego de l'application


# Outils et technologies de développement

* Android Studio est un environnement de développement pour développer des applications mobiles Android.
![](Image_readme/android_studio_lego.png)

* Retrofit2 est une librairie permettant de réaliser des appels à des webservices REST sur 
![](Image_readme/retrofit_lego.jpg)

* SharedPreferences sont des espaces de stockages propres à chaque application Android. Avec un système de clé/valeur, vous pourrez persister vos données facilement
![](Image_readme/sharedprefs.png)

# Consignes respectées :
* Architecture MVC
* Appels REST
* Ecrans : 2 activités, 2 fragments
* Affichage d'une liste dans un RecyclerView
* Affichage du détail d'un item de la liste
* Gitflow propre

# Fonctionnalités:
## Premier Ecran 
* Afficher la liste des films de studio ghilbi

![](Image_readme/Ecran_d'accueil_Application.PNG){:height="50%" width="50%"}

## Deuxième Ecran 
* afficher les details de chaque film(Titre, Année, Producteur, Réalisateur, Note de film...) 

![](Image_readme/detail_film1.PNG)
![](Image_readme/detail_film2.PNG)