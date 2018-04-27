FROM java:8-jre
WORKDIR usr/src
ADD ./target/MovieCruiser-MovieServices-489706-0.0.1-SNAPSHOT.jar /usr/src/MovieCruiser-MovieService.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "MovieCruiser-MovieService.jar"]