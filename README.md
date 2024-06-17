# MSPR_KAWA

## Infomations

Login-MDP pour KeyCloak : adminKeyCloak - adminKeyCloak

## application-local.properties

J'ai mis en place un fichier application-local.properties de mon coté en local.    
Pour pas que la config BDD individuel ne pollue celle des autres.
J'ai donc supp les lignes dans le properties générale
Et les ai mis dans le fichier application-local.properties qui est ignoré ; )

Voici les lignes a remettre dans votre fichier perso et adapter à votre BDD
    

    spring.datasource.url=jdbc:mysql://localhost:3306/mspr_kawa_main
    spring.datasource.username=root
    spring.datasource.password=
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

## Docker
Ajouter le dossier init_docker_db à la racine pour recevoir le fichier de la bdd SQLite.

**Se placer à la racine du projet pour executer les commandes suivantes.**

### Build l'image docker de l'API

Faire un `mvn clean install` puis start MainApplication.java et vérifier dans target le mspr_KAWA_clients.jar existe

    docker build -t mspr_kawa_XXXXX .

### Run le conteneur de l'API

    docker run --name mspr_kawa_XXXXX -d -p 8080:8080 mspr_kawa_XXXXX

Test de jenkins