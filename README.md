# Spring Boot Adapter

Spring Boot Adapter is an example of an API adapter. Sometimes there is a need to be able to support multiple different protocols working on top of HTTP, ie. do integration. Assumption here is that there are two forms of communication the clients to this system are using. One is native to the system but the other one is not, it needs to be adapted so that communication coming from both clients can be forwarded to the same backing API for the actual processing.

Both of the protocols may use different kind of authentication/authorization, we'll be using filters to deal with that. Both of the protocols are also based on a communication pattern where there are only a few request-responses per individual interaction but there might be a lot of separate interactions.

Work In Progress, code NOT COMPLETE.

## Run/dev

Containers are nice, use containers. Not hard to imagine we're using Docker here (https://www.docker.com/). You'll also need Java 8, check `java -version`. Java is all right. Build system is Gradle (https://gradle.org/), install it, it's cool.

* Clone the repo
* `cd spring-boot-adapter`
* `gradle build buildDocker`
* `docker run -p 8080:8080 -t --env-file ./env.list juharin/spring-boot-adapter` (or use the shell script `./run.sh`)
* `curl -X POST ...`

## What's going on

Documentation TBA.
