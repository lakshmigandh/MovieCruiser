FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
ENV MYSQL_USER=root
ENV MYSQL_ROOT_PASSWORD=root
ADD ./target/MovieCruiser-UserServices-489706-0.0.1-SNAPSHOT.jar /usr/src/MovieCruiser-UserService.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar", "MovieCruiser-UserService.jar"]
