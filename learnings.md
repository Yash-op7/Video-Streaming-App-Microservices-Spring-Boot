## 1. Eureka Service 
- Dependencies:
    - Eureka Server
    - Spring Web
- first add the `@EnableEurekaServer` annotation this will activate the Eureka related configuration for this application. It'll enable the eureka service registry server.
- Set up the ports in `application.properties`, this application will have multiple microservices we can't have everything running on one port, so...
    - For the eureka server 8761 is the default port
    - eureka.client.register-with-eureka=false &
eureka.client.fetch-registry=false because we don't want this service to register itself to itself so we set this default true flags to false:
        - first one prevents it from registering itself with eureka.
        - second one prevents it from downloading information of all registered applications since it already has that data.

## 2. movie-catalag-service
- Dependencies:
    - Spring Web
    - Eureka Discovery Client: This allows us to connect to the eureka server and register this service to the service registry
- First add this annotation in the main class: `@EnableDiscoveryClient`, this enables the configuration to automatically connect to the eureka discovery server or the service registry
- Configure application.properties:
    - `eureka.client.service-url.defaultZone=http://localhost:8761/eureka` needs to be provided to let this service know where the service registry is running, this is the only configuration we need to do to register this service into the service registry
- Run it to test
- Go the eureka server: localhost:8761, you'll see your service registered:
    - `AMI`s and Availabilty Zones: More than once instance of the microservices...
### Now we write the business logic for movie-catalog-service
- This service is responsible for managing the metadata of the videos, so we connect to a SQL db via Spring Data JPA
- Make a few controllers for CRUD of the data on the db
- Submit the metadata via a post req to the db
- Firstly add the dependencies for Spring Data JPA