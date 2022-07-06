#!bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=pt-springboot

echo "> Copy to build files"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Check the pid of the currently running application"

#CURRENT_PID=$(pgrep -fl pt-springboot | grep jar | awk '{print $1}')
CURRENT_PID=lsof -t -i :8080

echo "> pid of the currently running application: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> Currently, there is no running application, so it does not close"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> distribute the new application"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> Add execute permission to $JAR_NAME"

chmod +x $JAR_NAME

echo "> $JAR_NAME run"

nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
  -Dspring.profiles.active=real \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

