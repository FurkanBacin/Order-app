FROM openjdk:11 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw clean package -DskipTests

FROM openjdk:11
WORKDIR orderapp
COPY --from=build target/*.jar orderapp.jar
ENTRYPOINT ["java", "-jar", "orderapp.jar"]