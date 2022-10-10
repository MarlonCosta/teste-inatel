FROM maven:3.6.3-amazoncorretto-11 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM amazoncorretto:11-alpine
MAINTAINER mrlnrnn@gmail.com
CMD mvn clean package -d
COPY target/quotation-management-1.0.0.jar quotation-management-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/quotation-management-1.0.0.jar"]