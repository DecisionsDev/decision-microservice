#!/usr/bin/env bash

export MAVEN_HOME=$(dirname $MAVEN_CONFIG)
export MAVEN_OPTS="-Duser.home=$MAVEN_HOME"

cd decisionMicroservice/decisionMicroserviceIT/
mvn clean test -Dtest=$1 -Dtesthost=$2