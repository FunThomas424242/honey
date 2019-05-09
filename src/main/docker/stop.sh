#!/usr/bin/env sh

docker stop node-chrome
docker rm node-chrome
docker stop node-firefox
docker rm node-firefox
docker stop selenium-hub
docker rm selenium-hub
