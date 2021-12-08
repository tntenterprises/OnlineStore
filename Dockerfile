FROM openjdk:11
COPY ./target/onlinestore-0.0.1-SNAPSHOT.jar /use/src/onlinestore
WORKDIR /usr/src/onlinestore
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "onlinestore-0.0.1-SNAPSHOT.jar"]