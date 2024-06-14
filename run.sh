#!/bin/bash
docker build --no-cache -t t1-kafka-actuator:latest .
docker-compose -f ./docker/docker-compose.yml down --volumes
docker rm -f t1-kafka
docker rm -f t1-kafka-producer
docker rm -f t1-cakfa-consumer
docker-compose -f ./docker/docker-compose.yml build --no-cache
docker-compose -f ./docker/docker-compose.yml up --remove-orphans
