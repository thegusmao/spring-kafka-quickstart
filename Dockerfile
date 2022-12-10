FROM registry.access.redhat.com/ubi8/openjdk-11:1.10 AS builder
#FROM registry.access.redhat.com/ubi8/openjdk-8:1.3 AS builder
#RUN yum install maven
COPY . .
RUN mvn clean package

FROM registry.access.redhat.com/ubi8/openjdk-11:1.10
#FROM registry.access.redhat.com/ubi8/openjdk-8:1.3

COPY --from=builder /target/kafka-backend-0.0.1-SNAPSHOT.jar /home/jboss/app.jar

CMD ["java", "-jar", "/home/jboss/app.jar"]