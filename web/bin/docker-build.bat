@echo off
rem /**
rem  * Copyright (c) s
rem  * No deletion without permission, or be held responsible to law.
rem  *
rem  * Author: joey_huang@yeah.net
rem  */
echo.
echo [��Ϣ] ���Web���̣�����Docker����
echo.

%~d0
cd %~dp0

cd ..
call mvn clean package docker:remove docker:build -Dmaven.test.skip=true -U

cd bin
pause