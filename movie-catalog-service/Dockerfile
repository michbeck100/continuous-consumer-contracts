FROM openjdk:8-jdk-alpine
ADD target/movie-catalog-service.jar /srv/movie-catalog-service.jar
ENTRYPOINT java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \
  -Djava.security.egd=file:/dev/./urandom \
  -jar /srv/movie-catalog-service.jar
