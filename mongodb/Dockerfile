FROM mongo:latest

ADD mongodb/init.sh /init.sh
ADD mongodb/dump /
ADD mongodb/mongo-backup-s3.sh /mongo-backup-s3.sh
ADD mongodb/template.s3cfg /template.s3cfg

RUN \
    chmod +x /*.sh && \
    apt-get update && apt-get dist-upgrade -y --force-yes && \
    apt-get install wget gnupg psmisc -y -q && \
#     rm -rf /var/cache/* && rm -rf /var/lib/apt/lists/* \
    wget -O- -q http://s3tools.org/repo/deb-all/stable/s3tools.key | apt-key add - && \
    wget -O/etc/apt/sources.list.d/s3tools.list http://s3tools.org/repo/deb-all/stable/s3tools.list && \
    apt-get install s3cmd gettext-base -y
#     apt-get autoremove -y && apt-get clean && \


RUN bash -c 'envsubst < "/template.s3cfg" > "/data/db/.s3cfg"'

ENTRYPOINT ["/init.sh"]
