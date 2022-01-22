#!/bin/bash

PROJECT=DL-Server

PID=$(pgrep -f "$PROJECT*.jar")

if [[ $PID != "" ]]; then
  echo "Error : $PROJECT is not running."
  exit
fi

kill -9 $PID
echo $PROJECT is stopped.