#!/bin/bash
set -e
currentDir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
rootDir="$currentDir/../"

(cd "$rootDir" && exec ./gradlew -p tests/performance clean gatlingRun --stacktrace\
 -DstartDocker=false -DenvFile=`pwd`/config/docker/env/performance.env)