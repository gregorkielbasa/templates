FROM tomcat:latest
LABEL authors="arbeit"

COPY security-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
CMD ["java","-jar","/app/security-0.0.1-SNAPSHOT.jar"]