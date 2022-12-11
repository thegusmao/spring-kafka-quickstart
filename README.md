# AMQ Streams Spring Boot Quickstart

## Description

Repository dedicated to the AMQ Streams quickstart application. This application is written using Spring Boot.

## How to Deploy ?

### Prerequisites

* Maven >= 3.6.0
* Open JDK 11
* Openshift Client >= 4.x

### Process

#### Generate the JKS and save as secret

To connect with Kafka through SSL, first, generate a JKS key with the Kafka cluster certificate:

~~~console
oc extract secret/<KAFKA CLUSTER NAME>-cluster-ca-cert --keys=ca.crt --to=- > ca.crt
keytool -import -trustcacerts -alias root -file ca.crt -keystore truststore.jks -storepass password -noprompt
~~~

~~~console
oc create secret generic app-truststore --from-file=truststore.jks
~~~

#### Create the config map with the App properties

Before create the configmap, edit the properties with the values from AMQ Streams:
Get from the kafka bootstrap service
- spring.kafka.bootstrap-servers

Get the value in user secret, generated after you create the KafkaUser CR
- spring.kafka.consumer.properties.sasl.jaas.config
- spring.kafka.producer.properties.sasl.jaas.config

Use the path you are going to mount the truststore, like `/tmp/amq`
- spring.kafka.consumer.ssl.trust-store-location
- spring.kafka.producer.ssl.trust-store-location

~~~console
oc create -f ocp/configmap.yaml
~~~

#### Deploy the app and attach env and truststore

~~~console
oc new-app --name=app-kafka ubi8-openjdk-11:1.10~https://github.com/thegusmao/spring-kafka-quickstart.git
~~~

~~~console
oc set env --from=configmap/app-properties deployment/app-kafka

oc set volume deployment/app-kafka --add --name=truststore --type=secret --secret-name='app-truststore' --mount-path=/tmp/amq
~~~

#### Test the app

~~~console
oc exec <APP_POD> -- \
curl --request POST \
  --url http://localhost:8080/api/events \
  --header 'Content-Type: application/json' \
  --data '{
	"system": "sistema de cobranca",
	"operation": "CREATE"
}'
~~~

## In case of questions, please get in touch
* agusmao@redhat.com 
