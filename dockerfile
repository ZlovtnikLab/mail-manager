FROM adoptopenjdk/openjdk11

WORKDIR /app

COPY gradle/ ./gradle
COPY gradlew settings.gradle ./
COPY src ./src

RUN ./gradlew build

ADD build/libs/mail-controller-0.0.1-SNAPSHOT.jar mail-controller-0.0.1-SNAPSHOT.jar
EXPOSE 8080:8080 8080:8081
CMD java -server -XX:+UseSerialGC -XX:+ExitOnOutOfMemoryError -jar mail-controller-0.0.1-SNAPSHOT.jar