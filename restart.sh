#!/bin/bash

if [ $# -lt 1 ]; then
  ENV=dev
  echo "ENV is dev"
else
  ENV=$1
  echo "ENV is $ENV"
fi

./stop.sh
sleep 10
./start.sh $ENV