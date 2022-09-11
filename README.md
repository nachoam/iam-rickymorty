# iam-rickymory

## Approach
I've developed an API system that returns
the first appearance date of a character in the “Rick And Morty” TV series
and the episodes list using a clean architecture + DDD with Spring Boot 2.

The application is divided into 3 domains:
* App: Main class, Interface for principal Use Case, Infrastructure with Spring Boot 2
* Character
* Episode

Through the repository pattern we control the functionalities for each domain and implement then within 
infraestructure packages.

In this case we use the REST API as a data repository, decoupling it from the domain through IoC (facilitate the change of technology).

## Enviroment requirements
- Java > version Corretto 1.8.0_342

## Technologies
- Spring Boot 2 > version 2.7.3
- Lombok (Reduce boilerplate code)
- Webflux (API REST Client)
- Swagger 2 (API Documentation)

## Building

To build the entire Gradle project, you should run the following in the root of the checkout.

```
./gradlew build
```

This will compile all the code and run all the tests. 

## Testing Application

If you want to run only test, you should execute:

```
./gradlew test
```
## Start App

To run the project, you should execute:

```
./gradle bootRun
```
## API Documentation

The documentation is accesible at the url:

[API documentation](http://localhost:3456/swagger-ui/)
