FROM openjdk:8
ADD build/libs/emergency-0.1.0.jar emergency.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "emergency-0.1.0.jar"]