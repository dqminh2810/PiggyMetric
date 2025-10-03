#!/bin/bash
#Force file syncronization and lock writes
#mongosh admin --eval "printjson(db.fsyncLock())"

MONGODUMP_PATH="/usr/bin/mongodump"

TIMESTAMP=`date +%F-%H%M`
AWS_S3_BUCKET_NAME=$AWS_S3_BUCKET_NAME
AWS_S3_BUCKET_PATH="mongodb-backups"

# Create backup
$MONGODUMP_PATH -u $MONGO_INITDB_ROOT_USERNAME -p $MONGO_INITDB_ROOT_PASSWORD -d $MONGO_INITDB_DATABASE

# Add timestamp to backup
mv dump mongodb-$HOSTNAME-$TIMESTAMP
tar cf mongodb-$HOSTNAME-$TIMESTAMP.tar mongodb-$HOSTNAME-$TIMESTAMP

# Upload to S3
s3cmd put mongodb-$HOSTNAME-$TIMESTAMP.tar s3://$AWS_S3_BUCKET_NAME/$AWS_S3_BUCKET_PATH/mongodb-$HOSTNAME-$TIMESTAMP.tar

#Unlock database writes
#mongosh admin --eval "printjson(db.fsyncUnlock())"
#Delete local files
rm -rf mongodb-*
