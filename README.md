# Spring-Playground

A repository that implements all CRUD methods on a Person table using Spring Boot. 

## Getting Started

Install the .jar file needed for running the app
```bash
./mvnw clean install -DskipTests=true 
```

If you do not have the image built locally, run this

```bash
docker build ./ -t springbootapp && docker-compose up 
```

Else run
```bash
docker-compose up
```