FROM openjdk:8-alpine
ADD target/backend-bankya-1.0.0.jar /usr/share/bankya_backend.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/bankya_backend.jar"]