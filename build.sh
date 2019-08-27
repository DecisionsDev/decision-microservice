#!/usr/bin/env bash
set -e
export MAVEN_OPTS=-Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

if [[ -z "${ODMLIB}" ]]; then
echo "ODMLIB is not set. You should define an ODMLIB env variable on that location ODM_INSTALLATION/executionserver/lib"
  exit 1
fi

if [[ -z "${ODMBUILD}" ]]; then
echo "ODMBUILD is not set. You should define an ODMBUILD env variable on that location ODM_INSTALLATION/buildcommand/rules-compiler"
  exit 1
fi

mvn -B install:install-file -Dfile=${ODMLIB}/j2ee_connector-1_5-fr.jar -DgroupId=j2ee_connector -DartifactId=j2ee_connector -Dversion=1.0.0 -Dpackaging=jar
mvn -B install:install-file -Dfile=${ODMLIB}/jrules-engine.jar -DgroupId=jrules-engine -DartifactId=jrules-engine -Dversion=1.0.0 -Dpackaging=jar
mvn -B install:install-file -Dfile=${ODMLIB}/jrules-res-execution.jar -DgroupId=jrules-res-execution -DartifactId=jrules-res-execution -Dversion=1.0.0 -Dpackaging=jar
mvn -B install:install-file -Dfile=${ODMLIB}/jrules-res-manage-tools.jar -DgroupId=jrules-res-manage-tool -DartifactId=jrules-res-manage-tool -Dversion=1.0.0 -Dpackaging=jar
mvn -B install:install-file -Dfile=${ODMBUILD}/rules-compiler.jar -DgroupId=rules-compiler -DartifactId=rules-compiler -Dversion=1.0.0 -Dpackaging=jar

cd decisionMicroservice/decisionMicroserviceCore
mvn -B clean install

cd ../decisionMicroserviceClient
mvn -B clean package

cd ../..

echo success
