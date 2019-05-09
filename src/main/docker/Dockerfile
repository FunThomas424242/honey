#
#
#

# pull base image.
FROM docker


# maintainer details
MAINTAINER Thomas Schubert "huluvu424242@gmail.com"

RUN export uid=1000 gid=1000 && \
    mkdir -p /home/developer && \
    mkdir -p /home/developer/.local/share/applications && \
    mkdir -p /home/developer/Schreibtisch/ && \
    echo "developer:x:${uid}:${gid}:Developer,,,:/home/developer:/usr/bin/sh" >> /etc/passwd && \
    echo "developer:x:${uid}:" >> /etc/group && \
    chown ${uid}:${gid} -R /home/developer

USER developer
ENV HOME /home/developer
WORKDIR /home/developer


ADD start.sh stop.sh /home/developer/

USER root
# Commandline
CMD /home/developer/${action}.sh

