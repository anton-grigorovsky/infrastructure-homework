#!/bin/bash
set -e
currentDir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
rootDir="$currentDir/../"

(cd "$rootDir" && exec ./gradlew -p tests/e2e test allureReport  --continue --clean \
 -DstartDocker=false -DenvFile=`pwd`/config/docker/env/e2e.env)