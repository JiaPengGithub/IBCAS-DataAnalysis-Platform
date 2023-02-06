@echo off
rem /**
rem  * Copyright (c)
rem  * No deletion without permission, or be held responsible to law.
rem  *
rem  * Author: joey_huang@yeah.net
rem  */
echo.
echo [��Ϣ] �����װ���̣�����jar���ļ���
echo.

%~d0
cd %~dp0

cd ..
call mvn clean install -Dmaven.test.skip=true -Ppackage

cd bin
pause