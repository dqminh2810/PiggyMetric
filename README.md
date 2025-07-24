
# PiggyMetric
Microservices for finance by Spring Boot 3

## Build Maven
mvn clean package -DskipTests 

## Build & Run Docker containers
- Micro services & databases
`docker-compose --profile db --profile ms up`
- Grafana & Prometheus
`docker-compose -f docker-compose-metric up`

## Hostname resolver
Docker containers communicate by Docker network and use their hostname in docker-compose to resolve their own container address.
In order to access to docker containers by their own name from host we need to change the hosts file to map their hostname to point to localhost

****C:\Windows\System32\drivers\etc\hosts folder on Windows & /etc/hosts on Linux****
`127.0.0.1 gateway auth-service experience-service account-service notification-service statistic-service account-mongodb notification-mongodb statistic-mongodb`

## ARCHITECTURE

### Components architecture

![Components_architecture](https://github.com/dqminh2810/PiggyMetric/blob/main/docs/PM-Components-Architecture.jpg)

### OAuthFlow architecture

![OAuthFlow_architecture]()
