FROM mongo:latest
WORKDIR /script
COPY dump .
COPY init.sh mongo-backup-s3.sh template.s3cfg ./
RUN chmod +x /*.sh && \
    apt-get update && apt-get dist-upgrade -y && apt-get install \
    s3cmd \
    gettext-base \
    psmisc -y && \
    envsubst < "/script/template.s3cfg" > "/data/db/.s3cfg"
ENTRYPOINT ["/init.sh"]
