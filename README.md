# PiggyMetrics
Microservices for personal finance application using Spring technology influenced by `https://github.com/RameshMF/PiggyMetrics/tree/master` with updates for relevant features in newer Spring Boot version `(2.0.3 --> 3.1.2)`, and some adjustments about functional and infra architecture.

SPA is also developed by Angular framework and deployed separately following client-server architecture, instead of using default static files serving in Spring Boot Tomcat server.

## Build & Run
### Docker
`. build.sh`

`. build.sh -r` or `. build.sh --rebuild` to rebuild docker images
### Hostname resolver
In order to access to docker containers by its name from host machine, we need to change the hosts file to map the hostname to point to localhost (127.0.0.1)

Add these lines to hosts file ****C:\Windows\System32\drivers\etc\hosts folder on Windows & /etc/hosts on Linux****

`127.0.0.1 gateway auth-service experience-service account-service notification-service statistic-service account-mongodb auth-mongodb notification-mongodb statistic-mongodb`

Now you are able to access to http://gateway:8000