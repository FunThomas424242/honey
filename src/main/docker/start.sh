#!/usr/bin/env sh
docker run -d -p 4444:4444 --name selenium-hub selenium/hub:3.4.0
docker run -d --name node-chrome --link selenium-hub:hub selenium/node-chrome:3.4.0
docker run -d --name node-firefox --link selenium-hub:hub selenium/node-firefox:3.4.0
