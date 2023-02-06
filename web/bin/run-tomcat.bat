@echo off
rem /**
rem  * Copyright (c)
rem  * No deletion without permission, or be held responsible to law.
rem  *
rem  * Author: joey_huang@yeah.net
rem  */
echo.
echo [��Ϣ] ʹ�� Spring Boot Tomcat ���� Web ���̡�
echo.

%~d0
cd %~dp0

cd ..
title %cd%
set "MAVEN_OPTS=%MAVEN_OPTS% -Xms512m -Xmx1024m"
call mvn clean spring-boot:run -Dmaven.test.skip=true

pause