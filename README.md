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


### Responses Samples

404 : 

```JSON
{
    "code": 404,
    "message": "Company Not Found",
    "additionalData": "Company Not Found"
}
```

500 : 

```JSON
{
    "code": 500,
    "message": "Sirene API Unreacheable",
    "additionalData": "Connection Timeout"
}
```

200 : 

```JSON
{
    "id": 309203727,
    "siret": "55203253400646",
    "nic": "00646",
    "geo_adresse": "17 Boulevard Haussmann 75009 Paris",
    "date_creation": "20031020",
    "nom_raison_sociale": "DANONE"
}
```

## Technical Details

### API Layer Breakdown

- Web Layer (Spring Controller) : Entry point for the REST API
- Service Layer 
- Business Layer for Business logic
- Repository Layer (not used)

The layered architecture make the solution highly testable, and reduce the dependency between the separated layers.

For simple applications, the service layers can be merged with the business layer, to avoid the architecture sink-hole anti-pattern, where layers are simple pass-through with no logic implemented in the layer.

### Microservice containerization

Google JIB was used to build the Docker image using maven (with no need for Docker Client or Docker Engine), we simple need to specify the Base Image, Registry, and username/password to access the registry if it's private.

The advantage of using JIB is to ease the integration with the DevOps environnement, and use lightweight jenkins slaves.

### Exception Handling

For handling exceptions, a custom ExceptionHandler is implemented by extending the `ResponseEntityExceptionHandler` provided by Spring.

The IzicapExceptionHandler class will handle all exceptions which are either instance of IzicapException type, or other generic exceptions thrown by the application.

## Monitoring

The project is generated using the Spring Intializr portal, with Spring Actuator enabled, thus, by default we can check the health of the application using the following endpoint : 

```sh
http://localhost:8089/actuator/health
```
For a fully fledged solution, we can think off adding custom endpoint using Micrometer and Prometheus to have a clear view the application is not functionning properly (using timers for example)

## Testing

Not Included, but it is advised to the the GIVEN WHEN THEN style of representing unit tests.

## Other Considerations for production ready systems (not included)

- TLS 1.3 Configuration by adding the appropriate parameters into the application.yml file (the certificate must be signed by a reel Certification Authority in production) :

```sh
# enable/disable https
server.ssl.enabled=true
# keystore format
server.ssl.key-store-type=PKCS12 OR JKS
# keystore location
server.ssl.key-store=classpath:keystore/keystore.p12
# keystore password
server.ssl.key-store-password=changeit
```

- Caching and Cache Eviction (but having regard to the requirements, a Cache eviction strategy might not be needed)
- DDOS protection using WAF (F5 for example)
- Ports Scanning on the host VM.
- VM Hardening

