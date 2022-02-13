#!/bin/bash

PROJECT=DL-Server

PID=$(pgrep -f "$PROJECT*")

if [[ $PID != "" ]]; then
  echo "Error : $PROJECT is already running."
  exit
fi

JAR=./build/libs/DL-Server*.jar

if [ $# -lt 1 ]; then
  ENV=dev
  echo "ENV is dev"
else
  ENV=$1
  echo "ENV is $ENV"
fi

nohup java -Dspring.profiles.active=$ENV -jar $JAR &
echo Please wait 10s.