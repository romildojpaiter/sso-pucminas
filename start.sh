#!/bin/bash

source ~/.bash_profile

mvn clean package
STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Deployment Successful"
else
echo "Deployment Failed"
fi

echo Iniciando Autorization
java -jar authorization/target/authorization-server-1.0-SNAPSHOT.jar &

echo Iniciando Resource
java -jar resource/target/resource-server-1.0-SNAPSHOT.jar &

echo Iniciando Agendamento-UI
java -jar agendamento-ui/target/agendamento-ui-1.0-SNAPSHOT.jar &

exit $?