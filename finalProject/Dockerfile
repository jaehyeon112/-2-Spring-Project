FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV TZ=Asia/Seoul
<<<<<<< HEAD
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=linux","/app.jar"]
=======
ENTRYPOINT ["java","-jar","/app.jar"]
>>>>>>> c6c5aa0c013caa94f4bca204a00da98514744b68
