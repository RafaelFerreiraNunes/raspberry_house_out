FROM maven:3.9-amazoncorretto-25 AS build

RUN yum install -y util-linux && yum clean all

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM amazoncorretto:25-al2023

RUN yum install -y libgpiod && yum clean all

WORKDIR /app

EXPOSE 8090
EXPOSE 9090

COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]