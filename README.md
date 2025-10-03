
# PiggyMetric
Microservices for finance by Spring Boot 3

## Build Maven
`mvn clean package -DskipTests`

## Build & Run
- Microservices

`docker-compose --profile ms up`

- Databases

`Fill your .env MongoDB/AWS (if backup with S3) params`

`docker-compose --env-file .env --profile db up`

*Backup Data to S3*

`docker exec <mongodb-container-id> /bin/bash -c "/mongo-backup-s3.sh"`

## ARCHITECTURE

### Components architecture
![Components_architecture](https://github.com/dqminh2810/PiggyMetric/blob/main/docs/PM-Components-Architecture.png)

### OAuthFlow architecture
![OAuthFlow_architecture](https://github.com/dqminh2810/PiggyMetric/blob/main/docs/PM-OauthAppFlow.jpg)

## RELEVANT
[CONFIG](https://github.com/dqminh2810/PiggyMetricConfig)
[CICD](https://github.com/dqminh2810/PiggyMetricCICD)