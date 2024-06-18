#!/bin/bash
docker build --no-cache -t t1-kafka-actuator-parent:latest .
docker-compose -f ./docker/docker-compose.yml down --volumes
docker rm -fv t1-kafka-db
docker rm -fv t1-zookeper
docker rm -fv t1-kafka
docker rm -fv t1-kafka-producer
docker rm -fv t1-kafka-consumer
docker-compose -f ./docker/docker-compose.yaml build --no-cache
docker-compose -f ./docker/docker-compose.yaml up --force-recreate --remove-orphans
