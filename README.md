# Sirene API SAMPLE

The aim of this project is to provide a microservice that queries the following public Sirene API: _https://entreprise.data.gouv.fr/api/sirene/v1/siret/55203253400646_

## Code Build

The project requires Java 11 and Maven 3.3.+ to build/run on development environnement.

To build the project : 

```sh
mvn clean install
```

To run the project : 

```sh
java -jar sirene-api-0.0.1-SNAPSHOT.jar
```

The microservice exposes one single REST Api (GET) :

```sh
http://localhost:8089/api/companyInfo/55203253400646
```

If the number of APIs provided by the microservice is growing, we can provide a swagger documentation by adding the following dependency to the `pom.xml` file :
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

## Technical Details

### API Layer Breakdown

- Web Layer (Spring Controller) : Entry point for the REST API
- Service Layer 
- Business Layer for Business logic
- Repository Layer (not used)

The entry point for the REST api is a Spring Controller
