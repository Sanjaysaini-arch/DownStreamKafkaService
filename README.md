# DownStreamKafkaService

Spring Boot with Kafka Consumer 
This Project covers how to use Spring Boot with Spring Kafka to Consume JSON message from Kafka topics

Start Zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties
Start Kafka Server
bin/kafka-server-start.sh config/server.properties
Create Kafka Topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic downstream1
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic downstream2
Publish to the Kafka Topic via Console
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic downstream1
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic downstream2
