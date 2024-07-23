# Description
Microservices for personal finance application developed by Spring Boot 3
## Build & Run
### Development environment
`docker-compose down --remove-orphans`

`yes | docker-compose system prune`

`docker-compose -f docker-compose.dev.yaml up -d`

`./init.dev.sh`

### Production environment
`./init.sh -b <module-name1> <module-name2> ...`

Rebuild selected module and docker images, if none `module-name` is specified then all modules and docker images will be rebuild. Docker container is initialized after build phase.

`./init.sh` 
Init docker container without rebuild if there exist image, else build.

### Hostname resolver
In order to access to docker containers by its name from host machine, we need to change the hosts file to map the hostname to point to localhost (127.0.0.1)

Add these lines to hosts file ****C:\Windows\System32\drivers\etc\hosts folder on Windows & /etc/hosts on Linux****

`127.0.0.1 gateway auth-service experience-service account-service notification-service statistic-service account-mongodb auth-mongodb notification-mongodb statistic-mongodb`

Now we are able to access http://gateway:8000

### Reference
Components architecture and also workflow described by the link bellow

[DIAGRAM](https://viewer.diagrams.net/?tags=%7B%7D&lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=PM.drawio#Uhttps%3A%2F%2Fdrive.google.com%2Fuc%3Fid%3D1nmZasrVd5d0Cm0rj1czmavtlG8exHV45%26export%3Ddownload)
