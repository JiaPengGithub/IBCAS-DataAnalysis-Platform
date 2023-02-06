@echo off
rem /**
rem  * Copyright (c)
rem  * No deletion without permission, or be held responsible to law.
rem  *
rem  * Author: joey_huang@yeah.net
rem  */
echo.
echo [��Ϣ] ���Web���̣�������Web���̡�
echo.

%~d0
cd %~dp0

rem ���Web���̣���ʼ��
cd ..
call mvn clean package spring-boot:repackage -Dmaven.test.skip=true -U
cd target
rem ���Web���̣�������


rem ��������޸� web.jar Ϊ���� jar ������
mkdir app
copy web.war app
cd app
jar -xvf web.war
del web.war
cd WEB-INF
call startup.bat

pause