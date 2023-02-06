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
call mvn clean install -Dmaven.test.skip=true -Ppackage

cd bin
pause