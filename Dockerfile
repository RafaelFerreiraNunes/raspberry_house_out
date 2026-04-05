FROM maven:3.9-amazoncorretto-25 AS build

RUN yum install -y util-linux && yum clean all

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:25-jdk-noble

RUN apt-get update && apt-get install -y \
    libgpiod-dev \
    curl \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

EXPOSE 8090
EXPOSE 9090

COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]