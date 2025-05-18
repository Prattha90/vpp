#!/bin/sh

docker-compose -p powerplant-web-services stop
docker rm powerplant-api sonar-qube postgres-db
docker rmi powerplant-api-img

