#!/bin/bash

mvn -f /EurekaService\pom.xml spring-boot:run &
mvn -f /GatewayService\pom.xml spring-boot:run &

wait
