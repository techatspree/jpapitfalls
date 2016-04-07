#!/usr/bin/env bash

docker run --name jpapitfalls-rdbms --detach --publish :5432:5432 jpapitfalls-postgres

docker run --interactive --tty\
  --publish :8080:8080 --publish :9090:9090\
  --name jpapitfalls-app\
  jpapitfalls-appserver\
  /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0
