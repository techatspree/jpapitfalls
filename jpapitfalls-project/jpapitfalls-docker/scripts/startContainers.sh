#!/usr/bin/env bash

docker run --name jpapitfalls-rdbms --detach --publish :5432:5432 jpapitfalls-postgres
