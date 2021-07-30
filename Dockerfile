FROM openjdk:11
COPY target/poc-product-ms.jar poc-product-ms.jar
EXPOSE 9095
ENTRYPOINT ["java", "-jar", "poc-product-ms.jar"]