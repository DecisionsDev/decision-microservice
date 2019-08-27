#!/usr/bin/env bash

containerPort=9095

function checkError {
    if [ $1 -ne 0 ]
        then
                echo $2 does not build
                exit 1
        fi
}

# parameters :
# 1 : option file name
# 2 : test class name
# 3 : service path
# 4 : language
function runTest {
    ./generate-image.sh -i samples/options/$1 -p serverPort=$containerPort -p dockerName=test-image -p servicePath=$3 -f $4
    checkError $? image
    docker network create testnetwork

    # launching service container
    id=$(docker run -d -p $containerPort:$containerPort --network=testnetwork --name testcontainer test-image )
    sleep 5

    # launching tests
    docker run --rm -it \
    -w /gen \
    -e MAVEN_CONFIG=/var/maven/.m2 \
    -e ODMLIB=/ODMlib \
    -v "${ODMLIB}:/ODMlib" \
    -v "${PWD}:/gen" \
    -v "${HOME}/.m2/repository:/var/maven/.m2/repository" \
    --entrypoint /gen/test-docker-entrypoint.sh \
    --network=testnetwork \
    maven:3.6.1-ibmjava $2 testcontainer:$containerPort

    mvnExitCode=$?

    docker rm -f $id
    docker rmi -f test-image
    docker network rm testnetwork
    checkError $mvnExitCode $2
}

runTest $@