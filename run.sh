#!/usr/bin/sh
docker run -e "action=$1" -ti -v /var/run/docker.sock:/var/run/docker.sock funthomas424242/liona.docker
