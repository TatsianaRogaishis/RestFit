@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      http://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  RestFitApp startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and REST_FIT_APP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\RestFitApp-0.0.1.jar;%APP_HOME%\lib\postgresql-42.0.0.jar;%APP_HOME%\lib\postgresql-9.0-801.jdbc4.jar;%APP_HOME%\lib\commons-beanutils-1.9.3.jar;%APP_HOME%\lib\spring-boot-starter-thymeleaf-2.0.3.RELEASE.jar;%APP_HOME%\lib\thymeleaf-extras-springsecurity4-2.1.2.RELEASE.jar;%APP_HOME%\lib\bootstrap-3.2.0.jar;%APP_HOME%\lib\jquery-3.2.0.jar;%APP_HOME%\lib\spring-boot-starter-security-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-security-config-5.1.5.RELEASE.jar;%APP_HOME%\lib\spring-security-web-5.1.5.RELEASE.jar;%APP_HOME%\lib\spring-security-core-5.1.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-web-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-ws-core-3.0.7.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-data-jpa-2.1.9.RELEASE.jar;%APP_HOME%\lib\spring-xml-3.0.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-json-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.1.6.RELEASE.jar;%APP_HOME%\lib\spring-context-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-orm-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-jdbc-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-web-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-oxm-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-data-commons-2.1.9.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.1.8.RELEASE.jar;%APP_HOME%\lib\tomcat-embed-jasper-9.0.21.jar;%APP_HOME%\lib\jstl-1.2.jar;%APP_HOME%\lib\cxf-rt-frontend-jaxws-3.3.2.jar;%APP_HOME%\lib\cxf-rt-transports-http-3.3.2.jar;%APP_HOME%\lib\cxf-rt-frontend-simple-3.3.2.jar;%APP_HOME%\lib\cxf-rt-ws-addr-3.3.2.jar;%APP_HOME%\lib\cxf-rt-bindings-soap-3.3.2.jar;%APP_HOME%\lib\cxf-rt-databinding-jaxb-3.3.2.jar;%APP_HOME%\lib\cxf-rt-wsdl-3.3.2.jar;%APP_HOME%\lib\cxf-rt-ws-policy-3.3.2.jar;%APP_HOME%\lib\wsdl4j-1.6.3.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\thymeleaf-spring5-3.0.11.RELEASE.jar;%APP_HOME%\lib\thymeleaf-extras-java8time-3.0.4.RELEASE.jar;%APP_HOME%\lib\thymeleaf-3.0.11.RELEASE.jar;%APP_HOME%\lib\HikariCP-3.2.0.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.1.6.RELEASE.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\log4j-to-slf4j-2.11.2.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.26.jar;%APP_HOME%\lib\slf4j-api-1.7.26.jar;%APP_HOME%\lib\spring-expression-5.1.8.RELEASE.jar;%APP_HOME%\lib\spring-core-5.1.8.RELEASE.jar;%APP_HOME%\lib\hibernate-validator-6.0.17.Final.jar;%APP_HOME%\lib\javax.transaction-api-1.3.jar;%APP_HOME%\lib\cxf-rt-bindings-xml-3.3.2.jar;%APP_HOME%\lib\cxf-core-3.3.2.jar;%APP_HOME%\lib\jaxb-xjc-2.3.1.jar;%APP_HOME%\lib\jaxb-runtime-2.3.1.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\hibernate-core-5.3.10.Final.jar;%APP_HOME%\lib\spring-aspects-5.1.8.RELEASE.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\tomcat-embed-websocket-9.0.21.jar;%APP_HOME%\lib\tomcat-embed-core-9.0.21.jar;%APP_HOME%\lib\tomcat-embed-el-9.0.21.jar;%APP_HOME%\lib\ecj-3.16.0.jar;%APP_HOME%\lib\xml-resolver-1.2.jar;%APP_HOME%\lib\asm-7.0.jar;%APP_HOME%\lib\commons-io-2.5.jar;%APP_HOME%\lib\snakeyaml-1.23.jar;%APP_HOME%\lib\ognl-3.1.12.jar;%APP_HOME%\lib\attoparser-2.0.5.RELEASE.jar;%APP_HOME%\lib\unbescape-1.1.6.RELEASE.jar;%APP_HOME%\lib\spring-jcl-5.1.8.RELEASE.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.9.9.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.9.9.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.9.9.jar;%APP_HOME%\lib\jackson-databind-2.9.9.jar;%APP_HOME%\lib\validation-api-2.0.1.Final.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.4.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\classmate-1.4.0.jar;%APP_HOME%\lib\aspectjweaver-1.9.4.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\javax.persistence-api-2.2.jar;%APP_HOME%\lib\javassist-3.23.2-GA.jar;%APP_HOME%\lib\byte-buddy-1.9.13.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jandex-2.0.5.Final.jar;%APP_HOME%\lib\dom4j-2.1.1.jar;%APP_HOME%\lib\woodstox-core-5.0.3.jar;%APP_HOME%\lib\xmlschema-core-2.2.4.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.2.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar;%APP_HOME%\lib\jackson-core-2.9.9.jar;%APP_HOME%\lib\txw2-2.3.1.jar;%APP_HOME%\lib\istack-commons-runtime-3.0.7.jar;%APP_HOME%\lib\stax-ex-1.8.jar;%APP_HOME%\lib\FastInfoset-1.2.15.jar;%APP_HOME%\lib\stax2-api-3.1.4.jar;%APP_HOME%\lib\xsom-2.3.1.jar;%APP_HOME%\lib\codemodel-2.3.1.jar;%APP_HOME%\lib\rngom-2.3.1.jar;%APP_HOME%\lib\dtd-parser-1.4.jar;%APP_HOME%\lib\istack-commons-tools-3.0.7.jar;%APP_HOME%\lib\relaxng-datatype-2.3.1.jar;%APP_HOME%\lib\jakarta.activation-api-1.2.1.jar;%APP_HOME%\lib\neethi-3.1.1.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\log4j-api-2.11.2.jar;%APP_HOME%\lib\ant-1.10.2.jar;%APP_HOME%\lib\ant-launcher-1.10.2.jar

@rem Execute RestFitApp
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %REST_FIT_APP_OPTS%  -classpath "%CLASSPATH%" com.restfit.RestFitApp %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable REST_FIT_APP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%REST_FIT_APP_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
