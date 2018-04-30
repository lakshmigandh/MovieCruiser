#!/bin/bash
cd MovieServices
source ../env-props.sh
mvn clean package
cd ..
cd UserServices
source ../env-props.sh
mvn clean package
cd ..
