FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=movieDb
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/movieDb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_ROOT_PASSWORD=root123
ADD ./target/MovieCruiser-MovieServices-489706-0.0.1-SNAPSHOT.jar /usr/src/MovieCruiser-MovieService.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "MovieCruiser-MovieService.jar"]
