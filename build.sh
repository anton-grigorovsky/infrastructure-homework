sh gradlew sonar
if [ $? -eq 0 ]
then
  docker-compose build
else
  echo "Check sonar analysis report"
fi

sleep 60
