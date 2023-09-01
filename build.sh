#!/bin/bash
REBUILD_DOCKER="false"
while [ $# -ne 0 ]; do
    PARAM=`echo $1`
    case $PARAM in
        -r | --rebuild)
            REBUILD_DOCKER="true"
            ;;
        *)
            echo "ERROR: unknown parameter \"$PARAM\""
            exit 1
            ;;
    esac
    shift
done

echo "Init - Package jar services"
mvn clean package -DskipTests=true
echo "Done - Package jar services"
sleep 1

echo "Init docker containers"
docker-compose down
if [[ "$REBUILD_DOCKER" == "false" ]]; then
  echo "Init - Start docker containers"
  docker-compose up --remove-orphans
else
  echo "Init - Build and start docker containers"
  docker-compose up --build --remove-orphans
fi
echo "Done docker containers"