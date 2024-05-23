#!/bin/bash

# Supprimer le conteneur existant, s'il existe
#echo "Suppression du conteneur existant..."
#docker rm -f mspr-kawa-clients || true

# Supprimer l'image existante, s'il existe
echo "Suppression de l'image existante..."
docker rmi -f mspr-kawa-clients || true

# Construire l'image Docker
echo "Construction de l'image Docker..."
docker build -t mspr-kawa-clients .

# Lancer le conteneur
#echo "Lancement du conteneur..."
#docker run --name mspr-kawa-clients -d -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod mspr-kawa-clients

#echo "Déploiement terminé avec succès."
