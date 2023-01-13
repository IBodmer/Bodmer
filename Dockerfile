FROM openjdk:17
ADD /target/Bodmer-0.0.1-SNAPSHOT.jar back.jar
ENTRYPOINT ["java", "-jar","back.jar"]