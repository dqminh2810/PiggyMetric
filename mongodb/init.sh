#!/bin/bash
if test -z "$MONGO_DB_PASSWORD"; then
    echo "MONGODB_PASSWORD not defined"
    exit 1
fi

auth="-u $MONGO_DB_USERNAME -p $MONGO_DB_PASSWORD"

# MONGODB USER CREATION
(
echo "setup mongodb auth"
create_user="if (!db.getUser('$MONGO_DB_USERNAME')) { db.createUser({ user: '$MONGO_DB_USERNAME', pwd: '$MONGO_DB_PASSWORD', roles: [ {role:'readWrite', db:'$MONGO_INITDB_DATABASE'} ]}) }"
until mongo $MONGO_INITDB_DATABASE --eval "$create_user" || mongo $MONGO_INITDB_DATABASE $auth --eval "$create_user"; do sleep 5; done
killall mongod
sleep 1
killall -9 mongod
) &

# INIT DUMP EXECUTION
(
if test -n "$INIT_DUMP"; then
    echo "execute dump file"
	until mongo $MONGO_INITDB_DATABASE $auth $INIT_DUMP; do sleep 5; done
fi
) &

echo "start mongodb without auth"
chown -R mongodb /data/db
gosu mongodb mongod "$@"

echo "restarting with auth on"
sleep 5
exec gosu mongodb /usr/local/bin/docker-entrypoint.sh --auth "$@"
