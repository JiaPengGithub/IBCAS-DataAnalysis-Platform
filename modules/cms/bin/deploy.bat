@echo off
rem /**
rem  * No deletion without permission, or be held responsible to law.
rem  *
rem  */
echo.
echo.

%~d0
cd %~dp0

cd ..
call mvn clean deploy -Dmaven.test.skip=true -Pdeploy

cd bin
pause