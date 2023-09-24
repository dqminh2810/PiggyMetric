# PiggyMetrics
Microservices for personal finance application using Spring technology inspired by `https://github.com/RameshMF/PiggyMetrics/tree/master` with updates for relevant features in newer Spring Boot version `(2.0.3 --> 3.1.2)`, and some adjustments about functional and infra architecture. Build plan is also implemented helping us save some time in build & run phase.

SPA is also developed by Angular framework and deployed separately following client-server architecture, instead of using default static files serving in Spring Boot Tomcat server.

## Build & Run
### Development environment
`docker-compose down --remove-orphans`

`yes | docker-compose system prune`

`docker-compose -f docker-compose.dev.yaml up -d`

`./init.dev.sh`

### Production environment
`./init.sh -b <module-name1> <module-name2> ...`
or
`./init.sh --rebuild <module-name1> <module-name2> ...` 

to rebuild selected module and docker images, if none `module-name` is specified then all modules and docker images will be rebuild. Docker container is initialized after build phase.

`./init.sh` 
Init docker container and no rebuild if there exist image, else build.

### Hostname resolver
In order to access to docker containers by its name from host machine, we need to change the hosts file to map the hostname to point to localhost (127.0.0.1)

Add these lines to hosts file ****C:\Windows\System32\drivers\etc\hosts folder on Windows & /etc/hosts on Linux****

`127.0.0.1 gateway auth-service experience-service account-service notification-service statistic-service account-mongodb auth-mongodb notification-mongodb statistic-mongodb`

Now we are able to access http://gateway:8000 .
