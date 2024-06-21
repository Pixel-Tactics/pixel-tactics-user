FROM eclipse-temurin:17-alpine
RUN mkdir /opt/app
COPY target/pixel-tactics-user-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/pixel-tactics-user-0.0.1-SNAPSHOT.jar"]
