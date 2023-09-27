#!/bin/bash
REBUILD="false"
function getopts-extra () {
    declare i=1
    # if the next argument is not an option, then append it to array OPTARG
    while [[ ${OPTIND} -le $# && ${!OPTIND:0:1} != '-' ]]; do
        OPTARG[i]=${!OPTIND}
        let i++ OPTIND++
    done
}
function join_by {
  local d=${1-} f=${2-}
  if shift 2; then
    printf %s "$f" "${@/#/$d}"
  fi
}
(lsof -i:8888 -t | xargs -r kill && lsof -i:8761 -t | xargs -r kill && lsof -i:9000 -t | xargs -r kill && lsof -i:8080 -t | xargs -r kill && lsof -i:8081 -t | xargs -r kill && lsof -i:8082 -t | xargs -r kill && lsof -i:8083 -t | xargs -r kill && lsof -i:8000 -t | xargs -r kill)
while [ $# -ne 0 ]; do
    PARAM=`echo $1`
    case $PARAM in
        -b | --build)
            REBUILD="true"
            while getopts ":b:" opt
              do
                case $opt in
                  b)
                    getopts-extra "$@"
                    multi=( "${OPTARG[@]}" )
                    ;;
                  \?)
                    echo "Invalid option: -$OPTARG" >&2
                    exit 1
                    ;;
                esac
              done
            shift $((OPTIND -1))
            ;;
        *)
            echo "ERROR: unknown parameter \"$PARAM\""
            exit 1
            ;;
    esac
    shift
done
echo filename=.env > ./config/src/main/resources/.env.properties &&
echo filename=.env > ./registry/src/main/resources/.env.properties &&
echo filename=.env > ./auth-service/src/main/resources/.env.properties &&
echo filename=.env > ./experience-service/src/main/resources/.env.properties &&
echo filename=.env > ./account-service/src/main/resources/.env.properties &&
echo filename=.env > ./notification-service/src/main/resources/.env.properties &&
echo filename=.env > ./statistic-service/src/main/resources/.env.properties &&
echo filename=.env > ./gateway/src/main/resources/.env.properties &&
if [[ "$REBUILD" == "true" ]]; then
  if [[ ${multi[@]} != "" ]]; then   # Rebuild on select
    MAVEN_ARGUMENTS="--projects $(join_by , ${multi[@]}) --also-make clean package -DskipTests=true"
    DOCKER_COMPOSE_BUILD_ARGUMENTS=${multi[@]}
  else  # Rebuild all
    MAVEN_ARGUMENTS="clean package -DskipTests=true"
    DOCKER_COMPOSE_BUILD_ARGUMENTS=""
  fi
  echo ${multi[@]}
  echo "Init - Package jar services" &&
  mvn $MAVEN_ARGUMENTS &&
  echo "Done - Package jar services" &&
  echo "Init docker containers" &&
  docker compose down --remove-orphans &&
  yes | docker system prune &&
  docker compose build $DOCKER_COMPOSE_BUILD_ARGUMENTS &&
  docker compose up --no-build

else  # Only init default docker containers without rebuild anything
  echo "Init docker containers" &&
  docker compose down --remove-orphans &&
  yes | docker system prune &&
  docker-compose up
fi