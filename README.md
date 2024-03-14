# Kafka-examples
This repo contains Kafka Spring Boot Examples, currently, there are 3 Gradle projects one is Kafka producer, Kafka consumer and Streams using spring-boot
## Spring boot Kafka producer
Spring boot project inside weather-agents-simulator directory used to send demo temperature data to Kafka topic
## Spring boot Kafka Consumer
Spring boot Kafka consumer project inside the weather-consumer directory, to receive and save into Postgres DB.
## Spring boot Kafka Stream
Simple stream pipeline to filter extream wether and ingest into another kafka topic.
* [Stream Example](https://github.com/sheelprabhakar/kafka-examples/tree/main/weather-kafka-stream)

## Build

Use java 11

## Run with NR agent

    wget https://repo1.maven.org/maven2/com/newrelic/agent/java/newrelic-agent/8.4.0/newrelic-agent-8.4.0.jar -O build/newrelic.jar

    NEW_RELIC_LICENSE_KEY=<api-key> docker-compose up kafka producer consumer

## Run with OTel agent

    wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.33.0/opentelemetry-javaagent.jar -O build/otelagent.jar

    OTEL_EXPORTER_OTLP_HEADERS=api-key=<api-key> docker-compose --env-file .env.otelagent up kafka producer consumer