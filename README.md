# PiggyMetrics
Microservices for personal finance application using Spring technology influenced by `https://github.com/RameshMF/PiggyMetrics/tree/master` with some updates for relevant features in newer Spring Boot version `(2.0.3 --> 3.1.2)`

SPA is also developed by Angular framework and deployed separately following client-server architecture, instead of using default static files serving in Spring Boot Tomcat server.

## Development

### Init & start database
`docker-compose up -d`

### Init & start Microservices
`cd {{service-repository}}`

`mvn spring-boot:run`