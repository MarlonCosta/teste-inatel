FROM amazoncorretto:11-alpine
MAINTAINER mrlnrnn@gmail.com
CMD mvn clean package -d
COPY target/quotation-management-1.0.0.jar quotation-management-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/quotation-management-1.0.0.jar"]