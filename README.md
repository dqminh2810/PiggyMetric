
# Description
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
`127.0.0.1 gateway auth-service experience-service account-service notification-service statistic-service account-mongodb auth-mongodb notification-mongodb statistic-mongodb`

## Reference
Components architecture and also workflow described by the link bellow

[DIAGRAM](https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=PM.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1nmZasrVd5d0Cm0rj1czmavtlG8exHV45%26export%3Ddownload)