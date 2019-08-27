#!/usr/bin/env bash
set -e

tmpfolder=tmpdockerODMlib

if [[ -z "${ODMLIB}" ]]; then
echo "ODMLIB is not set. You should define an ODMLIB env variable on that location ODM_INSTALLATION/executionserver/lib"
  exit 1
fi

if [[ -z "${ODMBUILD}" ]]; then
echo "ODMBUILD is not set. You should define an ODMBUILD env variable on that location ODM_INSTALLATION/buildcommand/rules-compiler"
  exit 1
fi

mkdir $tmpfolder

cp $ODMLIB/j2ee_connector-1_5-fr.jar $tmpfolder/j2ee_connector-1_5-fr.jar
cp $ODMLIB/jrules-res-execution.jar $tmpfolder/jrules-res-execution.jar
cp $ODMLIB/jrules-res-manage-tools.jar $tmpfolder/jrules-res-manage-tools.jar
cp $ODMLIB/jrules-engine.jar $tmpfolder/jrules-engine.jar

cp $ODMBUILD/rules-compiler.jar $tmpfolder/rules-compiler.jar

docker build --build-arg ODMBUILD=./$tmpfolder --build-arg ODMLIB=./$tmpfolder -t servicegenerator .

rm -r $tmpfolder
