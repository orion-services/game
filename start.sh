#!/bin/bash
docker rm -f $(docker ps -a -q);docker system prune --volumes -a -f
mvn clean package
docker-compose up -d