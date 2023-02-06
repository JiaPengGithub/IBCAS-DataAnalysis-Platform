@echo off
rem /**
rem  * Copyright (c)
rem  * No deletion without permission, or be held responsible to law.
rem  *
rem  * Author: joey_huang@yeah.net
rem  */
echo.
echo [��Ϣ] ���Web���̣�����war/jar���ļ���
echo.

%~d0
cd %~dp0

cd ..
call mvn clean package spring-boot:repackage -Dmaven.test.skip=true -U

cd bin
pause