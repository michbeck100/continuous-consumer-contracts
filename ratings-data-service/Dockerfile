FROM openjdk:8-jdk-alpine
ADD target/ratings-data-service.jar /srv/ratings-data-service.jar
ENTRYPOINT java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \
  -Djava.security.egd=file:/dev/./urandom \
  -jar /srv/ratings-data-service.jar
