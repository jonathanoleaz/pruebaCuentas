FROM openjdk:8               # FROM<image>
VOLUME /tmp                   # Temporary location to run
EXPOSE 8081                   # Provide port number
ADD target/SpringBootDockerTest-0.0.1-SNAPSHOT.jar SpringBootDockerTest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/pruebacuentas.jar"]