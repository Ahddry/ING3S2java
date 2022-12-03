# Projet ING 3 S2 Java

Projet du deuxième semestre de troisième année à l'ECE en Informatique.

Application de gestion d'un cinéma en **Java** avec une interface graphique en **JavaFX**.

## A propos

Le but du projet est d'écrire une application de réservation de billets de cinéma qui permettra au client de réserver les billets pour le film de son choix. L'application aidera également la direction du cinéma à conserver ses dossiers de vente et de clients.

Rendu le 03/04/2021.

## Cahier des charges

### Extraits du cahier des charges

Dans ce programme, vous rédigerez un ensemble de classes de support pour une application de réservation de billets de cinéma. Le programme affiche la sélection de films en cours de lecture au cinéma. L'utilisateur doit pouvoir choisir le film et réserver le nombre de billets requis. Les clients peuvent être de deux types. Clients invités et clients membres. Les clients invités n'auront pas besoin de se connecter et pourront réserver les billets sans aucune réduction offerte. Les clients membres peuvent être réguliers, seniors ou enfants. Les clients membres auront besoin d'un login et pourront réserver les billets avec réduction offerte en fonction du type de membre.

L'application doit être développée pour deux types d'utilisateurs :

- Clients - Achat de billets de cinéma, calcul de la facture avec/sans réductions, navigation dans la disponibilité, etc.
- Employés - Mettez à jour les films en cours de lecture, introduisez diverses offres de réduction, maintenez les dossiers des clients, passez en revue les films populaires, etc.

Vous êtes censé concevoir et développer la base de données de cette application ainsi que les classes Java nécessaires à la mise en œuvre de l'application.

Selon le pattern MVC, votre interface graphique constitue la Vue (uniquement l'affichage) dépendante des actions de l'utilisateur (gestion des événements) au niveau du Contrôleur (modules Recherche, Mise à jour et reporting). Celui-ci demandera au Modèle de récupérer ou de mettre à jour - via le module d'accès aux données (DAO) - les informations de la base de données, de les organiser et de les assembler (par exemple, en les stockant dans des collections). Ensuite, le contrôleur demandera les données au modèle, les analysera, prendra des décisions et renverra le texte à la vue. Il vous est conseillé d'adopter le modèle MVC pour le développement d'un projet cohérent.

### Résumé du cahier des charges

- **Application graphique** : Interface graphique utilisant la bibliothèque de votre choix (Swing, AWT, JavaFX, etc.) permettant à un utilisateur de réserver des billets de cinéma.
- **Base de données** : Base de données relationnelle (MySQL, PostgreSQL, etc.) contenant les informations nécessaires à l'application, et communiquant avec celle-ci via JDBC.
- **Modèle MVC** : Modèle MVC (Modèle, Vue, Contrôleur) permettant de séparer les différentes parties de l'application.
- **Gestion des utilisateurs** : Gestion des utilisateurs (clients et employés) de l'application.
- **Gestion des films** : Gestion des films en cours de projection.
- **Gestion des salles et séances** : Gestion des salles de cinéma et des séances de projection.
- **Gestion des réservations** : Gestion des réservations de billets de cinéma, avec écran factice de paiement.

### Travail réalisé

Nous réalisé tous les points mentionnés précédemment, en implémentant notamment l'interface graphique en **JavaFX**.

De plus, nous avons implémenté des fonctionnalités supplémentaires :

- **Affichage des informations du film** : Affichage des informations du film sélectionné (résumé, durée, acteurs jouant dans le film, etc.).
- **Intégration des trailers** : Intégration des trailers des films en cours de projection.
- **Gestion des réductions** : Gestion des réductions pour les clients membres (réguliers, seniors, enfants).
- **Panneau d'administration** : Panneau d'administration permettant de gérer les films, les salles, les séances, les réductions, les utilisateurs, etc.
- **Base de données distante** : Base de données distante hébergée sur un serveur distant et non en local.
- **Script de Web scraping** : Script de Web scraping pour peupler la base de données avec des films existants.

## Ce que nous avons appris

Ce projet nous aura permis d'apprendre JavaFX, ainsi que de nombreux autres concepts de programmation orientée objet. Il nous aura également permis de découvrir le pattern MVC et de l'appliquer à notre projet. Ce fut également l'occasion pour nous de travailler en équipes, devant ainsi nous organiser et nous répartir efficacement les tâches, notamment en utilisant l'outil Trello.

## Installation

Le projet est écrit en **Java** et utilise la bibliothèque **JavaFX** pour l'interface graphique. Il est donc nécessaire d'avoir [**Java** 8](https://www.java.com/fr/download/) installé sur votre machine. Pour installer **JavaFX**, il suffit de suivre les instructions sur le site de [**JavaFX**](https://openjfx.io/). Pour installer le projet, il suffit de cloner le dépôt git et de l'ouvrir dans un IDE tel que Eclipse ou IntelliJ.

## Contributeurs

- Adrien Blair [@Ahddry](https://github.com/Ahddry)
- Donatien Chevillard [@donatien-chevillard](https://github.com/donatien-chevillard)
- Arthur Fournier [@space192](https://github.com/space192)
- Pierre-Louis Paillet [@pierrelouispaillet](https://github.com/pierrelouispaillet)

## Copyright

> Pour les étudiants, citez-nous si vous souhaitez réutiliser ce projet dans le cadre de vos propres travaux, ca vous évitera de vous faire prendre pour plagiat.
