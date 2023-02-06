#!/bin/sh
# /**
#  * Copyright (c)
#  * No deletion without permission, or be held responsible to law.
#  *
#  * Author: joey_huang@yeah.net
#  * 
#  */
echo ""
echo "[信息] 使用 Spring Boot Tomcat 运行 Web 工程。"
echo ""

cd ..
MAVEN_OPTS="$MAVEN_OPTS -Xms512m -Xmx1024m"
mvn clean spring-boot:run -Dmaven.test.skip=true