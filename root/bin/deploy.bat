@echo off
rem /**
rem  * Copyright (c)
rem  * No deletion without permission, or be held responsible to law.
rem  *
rem  * Author: joey_huang@yeah.net
rem  */
echo.
echo [��Ϣ] ���𹤳̰汾��Nexus��������
echo.

%~d0
cd %~dp0

cd ..
call mvn clean deploy -Dmaven.test.skip=true -Pdeploy

cd bin
pause