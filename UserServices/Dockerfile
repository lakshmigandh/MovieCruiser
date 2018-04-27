FROM java:8-jre
WORKDIR usr/src
ADD ./target/MovieCruiser-UserServices-489706-0.0.1-SNAPSHOT.jar /usr/src/MovieCruiser-UserService.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "MovieCruiser-UserService.jar"]