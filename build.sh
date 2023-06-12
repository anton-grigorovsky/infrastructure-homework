sh gradlew detekt
if [ $? -eq 0 ]
then
  sh gradlew sonar
  if [ $? -eq 0 ]
  then
    sh gradlew dependencyUpdate
    docker-compose build
  else
    echo "Check sonar analysis report"
  fi
else
  echo "Code style checking failed"
fi

sleep 60
