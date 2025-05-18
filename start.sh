#!/bin/sh

./gradlew clean build
docker-compose -p powerplant-web-services -f docker-compose.yml up -d
