#!/bin/bash
clear
docker compose down

# shellcheck disable=SC2164
./gradlew clean
./gradlew build -x test
docker buildx build -t twilight-backend .

source .env

docker compose up -d
docker compose logs -f backend