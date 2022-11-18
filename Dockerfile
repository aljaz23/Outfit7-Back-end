FROM openjdk:19

COPY . /project
WORKDIR /project

ENTRYPOINT ["./mvnw","spring-boot:run"]
