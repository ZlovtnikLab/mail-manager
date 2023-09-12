FROM adoptopenjdk/openjdk11

USER root

ADD build/libs/mail-controller-0.0.1-SNAPSHOT.jar mail-controller-0.0.1-SNAPSHOT.jar

CMD java -server -XX:+UseSerialGC -XX:+ExitOnOutOfMemoryError -jar mail-controller-0.0.1-SNAPSHOT.jar