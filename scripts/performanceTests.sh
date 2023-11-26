#!/bin/bash
set -e
currentDir=$(cd -P -- "$(dirname -- "$0")" && pwd -P)
rootDir="$currentDir/../"

(cd "$rootDir" && exec ./gradlew build -x :tests:e2e:test -PallWarningsAsErrors=true)
(cd "$rootDir" && exec ./buildImage.sh)
(cd "$rootDir" && exec ./gradlew -p tests/performance clean gatlingRun)