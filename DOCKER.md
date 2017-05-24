DOCKER
======

Create a Dockerfile in this directory:

`src/main/docker`

Then buildDocker task can be found in this directory:

`gradle/docker.gradle`

Don't forget to change the `appname`.

To build docker image:

`../gradlew bootRepackage buildDocker`

Then run:

`docker-compose -f src/main/docker/app.yml up -d`

