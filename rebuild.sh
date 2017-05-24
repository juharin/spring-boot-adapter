#! /bin/bash

docker stop $(docker ps | grep spring-boot-adapter | awk '{print $1}')
gradle build buildDocker
