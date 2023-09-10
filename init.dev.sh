#!/bin/bash
spring_boot_run='mvn spring-boot:run -DskipTests=true'
update_env_properties='echo filename=.env.dev > ./src/main/resources/.env.properties'

(lsof -i:9000 -t | xargs -r kill && lsof -i:8080 -t | xargs -r kill && lsof -i:8081 -t | xargs -r kill && lsof -i:8082 -t | xargs -r kill && lsof -i:8083 -t | xargs -r kill && lsof -i:8000 -t | xargs -r kill) & wait
#(docker-compose -f docker-compose.dev.yaml down --remove-orphans && yes | docker system prune && docker-compose -f docker-compose.dev.yaml up -d) & wait

(cd ./auth-service && eval $update_env_properties && eval $spring_boot_run) &
(sleep 5 && cd ./experience-service && eval $update_env_properties && eval $spring_boot_run) &
(sleep 5 && cd ./account-service && eval $update_env_properties && eval $spring_boot_run) &
(sleep 5 && cd ./notification-service && eval $update_env_properties && eval $spring_boot_run) &
(sleep 5 && cd ./statistic-service && eval $update_env_properties && eval $spring_boot_run) &
(sleep 5 && cd ./gateway && eval $update_env_properties && eval $spring_boot_run) &