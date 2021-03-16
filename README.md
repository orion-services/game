# Orion Game Service

Orion Game Service microservice

## Run with Docker Compose

The easer way to install and execute the Orion Game Service is to use docker-composer. Once Docker is installed, you can run the bellow commands:

    mvn clean package
    docker-compose up -d

Note: Default database root and password is: orion-game-service

## Docker image

To create a Docker image for Orion game Service:

    mvn clean package

    docker build -t orion-game-image .

    docker run -d --name orion-game-service -p 9080:9080 -p 9443:9443 orion-game-image

### Orion User dev mode

During development, you can use Liberty's development mode (dev mode) to code while observing and testing your changes on the fly.

    mvn clear package
    mvn liberty:dev

### Help scripts

To delete and restart all containers, images, and volumes, package and run the service.

    ./rebuild.sh

To quick start your project.

    ./restart.sh

### (For develop) build, run front-end

# install dependencies

$ npm install

# serve with hot reload at localhost:3000

$ npm run dev

# build for production and launch server

$ npm run build
$ npm run start

# generate static project

$ npm run generate
cp -a dist/* ../src/main/webapp
