#! /bin/bash

docker run -p 8080:8080 -t --env-file ./env.list juharin/spring-boot-adapter
