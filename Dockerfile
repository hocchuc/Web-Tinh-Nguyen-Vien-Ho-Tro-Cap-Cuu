FROM openjdk:8
ADD build/libs/emergency.jar emergency.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "emergency-0.1.0.jar"]