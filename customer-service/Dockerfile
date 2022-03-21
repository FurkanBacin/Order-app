FROM openjdk:11
ADD target/customer-service.jar customer-service.jar
EXPOSE 9002
ENTRYPOINT ["java","jar","/customer-service.jar"]