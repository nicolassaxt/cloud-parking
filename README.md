# cloud-parking
Projeto de gerenciamento de estacionamento com Java Spring REST 

## Requirements

- Java 11
- Docker
- https://www.postman.com/

## Spring Boot

- https://start.spring.io/

- Java 11

- Maven Project

- Spring Web

- Spring Data JPA

- Spring Security

- PostgreSQL Driver

## Run database
docker run --name parking-db -p 5433:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123 -d postgres:10-alpine

## Stop database
docker stop parking-db

## Start database
docker start parking-db

## Documentation Postman

- https://documenter.getpostman.com/view/17952036/VUqpscWx