@REM app launcher script
@REM
@REM Environment:
@REM JAVA_HOME - location of a JDK home dir (optional if java on path)
@setlocal enabledelayedexpansion

@echo off

cd %~dp0
cd ../

if "%JAVA_OPS%" == "" (
  set JAVA_OPS=-Dfile.encoding=utf-8 
  set JAVA_OPS=!JAVA_OPS! -Dconf.home=%cd%/conf
  set JAVA_OPS=!JAVA_OPS! -DLogback.configurationFile=%cd%/conf/logback.xml
  set JAVA_OPS=!JAVA_OPS! -server -Xmx128m -Xms128m -Xss256k
)
java %JAVA_OPS% -jar %cd%\lib\dubbo-demo-system-provider-1.0.jar