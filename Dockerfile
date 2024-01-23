FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /kiiiBalloonApp
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -DskipTests

FROM openjdk:17
WORKDIR /kiiiBalloonApp
COPY --from=build /kiiiBalloonApp/target/*.jar kiii.jar
EXPOSE 8080
CMD ["java", "-jar", "kiii.jar"]