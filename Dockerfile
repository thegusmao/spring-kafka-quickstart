FROM registry.access.redhat.com/ubi8/openjdk-11:1.10 AS builder
RUN dnf install maven
COPY / /
RUN mvn clean package

FROM registry.access.redhat.com/ubi8/openjdk-11:1.10

COPY --from=builder /target/kafka-backend-0.0.1-SNAPSHOT.jar /home/jboss/kafka-backend.jar

CMD ["java", "-jar", "/home/jboss/kafka-backend.jar"]