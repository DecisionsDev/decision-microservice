#!/usr/bin/env bash

set -e

./generate-in-docker.sh $@ -o tmpgen
cd tmpgen

if [ "$TRAVIS" = "true" ]
    then
        sudo chmod 777 *.sh
    else
        chmod +x *.sh
fi


./buildDocker.sh

cd ..

if [ "$TRAVIS" = "true" ]
    then
        sudo rm -r tmpgen
    else
        rm -r tmpgen
fi