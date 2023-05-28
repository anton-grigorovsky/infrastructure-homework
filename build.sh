sh gradlew dependencyCheckAnalyze
if [ $? -eq 0 ]
then
  sh gradlew sonar
  if [ $? -eq 0 ]
  then
    docker-compose build
  else
    echo "Found dependencies vulnerabilities"
  fi
else
  echo "Check sonar analysis report"
fi

sleep 60
