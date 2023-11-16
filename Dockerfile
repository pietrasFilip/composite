FROM openjdk:21
EXPOSE 8080
WORKDIR /composite

ADD target/composite.jar composite.jar
ENTRYPOINT ["java", "-jar", "composite.jar"]