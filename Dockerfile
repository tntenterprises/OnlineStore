FROM openjdk:11
COPY ./target/onlinestore-0.0.1-SNAPSHOT.jar /usr/src/app/
WORKDIR /usr/src/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "onlinestore-0.0.1-SNAPSHOT.jar"]