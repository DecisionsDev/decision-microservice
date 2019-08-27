#!/usr/bin/env bash

docker run --rm -v "${PWD}:/generation" servicegenerator "$@"
