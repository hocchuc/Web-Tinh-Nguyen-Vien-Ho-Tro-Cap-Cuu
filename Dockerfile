FROM openjdk:8
ADD target/Web-Tinh-Nguyen-Vien-Ho-Tro-Cap-Cuu-1.0.jar Web-Tinh-Nguyen-Vien-Ho-Tro-Cap-Cuu-1.0.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "Web-Tinh-Nguyen-Vien-Ho-Tro-Cap-Cuu-1.0.jar"]