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