#
#
#

# pull base image.
FROM archlinux/base


# maintainer details
MAINTAINER Thomas Schubert "huluvu424242@gmail.com"

# install the full configuration via puppet
RUN  \
  pacman -Sy && \
  pacman -S --noconfirm --color always docker && \
  systemctl enable docker.service && \
  systemctl enable docker.service

# Commandline
CMD docker run -d -p 4444:4444 --name selenium-hub selenium/hub:3.4.0 && \
    docker run -d --link selenium-hub:hub selenium/node-chrome:3.4.0 && \
    docker run -d --link selenium-hub:hub selenium/node-firefox:3.4.0 && \
    docker ps
