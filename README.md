# kafka-spring-sample repository

## Description



Repository dedicated to the Todo List backend application. This application is written using Spring Boot, following DDD and TDD principles.

This aplication aplication enables the testing of liveness and probes in 
a openshift envirioment, that is possibel with the /health/liveness and
/health/readness uris.

Is also possible to exercercise HPA using the uri /resource-worm with a simple get, that will simulate a high use of cpu and memory.


## How to Deploy ?

### Prerequisites

* Maven >= 3.6.0
* Open JDK 11
* Openshift Client >= 4.x

### Process

#### Generate the JKS 

To connect with Kafka through SSL, first, generate a JKS key with the Kafka cluster certificate:

~~~console
oc extract secret/<KAFKA CLUSTER NAME>-cluster-ca-cert --keys=ca.crt --to=- > ca.crt
keytool -import -trustcacerts -alias root -file ca.crt -keystore truststore.jks -storepass password -noprompt
~~~

#### Create the jks as secret

~~~
oc create secret generic app-truststore --from-file=truststore.jks
~~~

#### Create a config map

To build it execute the following commands:

~~~console
cd /tmp
git clone https://gitlab.consulting.redhat.com/consulting-brazil/quick-starts/spring-boot.git -b 2.4.9.Final-redhat-00001
cd spring-boot
~~~

## How to build ?

To build this application execute the following commands: 

~~~console
cd /tmp
git clone https://gitlab.consulting.redhat.com/consulting-brazil/quick-starts/spring-boot.git -b 2.4.9.Final-redhat-00001
cd spring-boot
mvn clean package -DskipTests
~~~

## How to run in self-contained mode ?

To run this application execute the following commands:

~~~console
cd /tmp
git clone https://gitlab.consulting.redhat.com/consulting-brazil/quick-starts/spring-boot.git -b 2.4.9.Final-redhat-00001
cd spring-boot
mvn spring-boot:run \ 
    mvn spring-boot:run -Dspring-boot.run.jvmArguments="-DTODO_LIST_EDITABLE=true -DPOKEMON_NAME=ditto -DMAX_RESPONSE_TIME=1000 -DDIVIDER=1"
~~~

The variables POKEMON_NAME, MAX_RESPONSE_TIME, DIVIDER exists to alter the
behavior of the health and liveness probes

* setting the value of DIVIDER to 0 will simulate a fail in the liveness probe
* setting the value of POKEMON_NAME to a not existent pokemon will simulate a fail in the readiness probe
* setting the value of MAX_RESPONSE_TIME to a small value(150-250) will cause the readiness probe to fail occasionally

## How to run in container (Docker) mode ?

To run this application execute the following commands:

~~~console
cd /tmp
git clone https://gitlab.consulting.redhat.com/consulting-brazil/quick-starts/spring-boot.git -b 2.4.9.Final-redhat-00001
cd spring-boot
mvn clean package
docker build -t <your-namespace>/todo-list-backend:0.0.1-SNAPSHOT --build-arg is_todolist_editable=true divider=1 pokemon_name="ditto" max_response_time=1000 .
docker run -p 8080:8080 <your-namespace>/todo-list-backend:0.0.1-SNAPSHOT
~~~

## In case of questions, please get in touch
* agusmao@redhat.com 
