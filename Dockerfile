FROM java:8-jre

CMD mvn clean && mvn package -DskipTests

ADD target/JenkinsManualService-0.0.1-SNAPSHOT.jar jenkinsmanualservice.jar
ENTRYPOINT ["java", "-jar", "/jenkinsmanualservice.jar"]

EXPOSE 9010
