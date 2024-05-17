FROM openjdk:17

WORKDIR /app

COPY target/mspr_KAWA_clients.jar /app/mspr_KAWA_clients.jar
COPY init_docker_db/ /app/init_docker_db

EXPOSE 8080:8080

CMD ["java", "-jar", "mspr_KAWA_clients.jar"]
