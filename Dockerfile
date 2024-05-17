FROM openjdk:17

WORKDIR /app

COPY target/mspr_KAWA_clients.jar /app/mspr_KAWA_clients.jar
COPY init_docker_dbb/ /app/init_docker_dbb

EXPOSE 8080:8080

CMD ["java", "-jar", "mspr_KAWA_clients.jar"]
