FROM openjdk:21-jdk-slim

WORKDIR extracted
ADD ./target/*.jar account-service.jar
RUN java -Djarmode=layertools -jar account-service.jar extract

FROM eclipse-temurin:25-jre-ubi10-minimal
WORKDIR application
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

LABEL maintainer="Sumeet Patil"

EXPOSE 8080

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]