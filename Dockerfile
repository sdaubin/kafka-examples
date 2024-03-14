FROM eclipse-temurin:11

RUN apt update && apt upgrade -y && apt install -y git

WORKDIR /app

COPY . /app

RUN ./gradlew build