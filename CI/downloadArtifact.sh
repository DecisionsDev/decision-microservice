#!/usr/bin/env bash

curl -u $ARTIF_USERNAME:$ARTIF_PASS $REPO_ADDR  --output artifact.zip
unzip artifact.zip
rm artifact.zip

