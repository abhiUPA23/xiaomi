FROM maven:3.8.6-openjdk-20 AS build
COPY . .
RUN mvn clean package -DskipTests 

FROM openjdk:20-jdk-slim
COPY --from=build /target/xiaomi-0.0.1-SNAPSHOT.jar xiaomi.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar","xiaomi.jar" ]
