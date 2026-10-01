@echo off
setlocal EnableDelayedExpansion

set "PROJECT_ROOT=%~dp0"
set "SOURCE_DIR=%PROJECT_ROOT%src\main\java"
set "RESOURCE_DIR=%PROJECT_ROOT%src\main\resources"
set "BUILD_DIR=%PROJECT_ROOT%build\classes"

echo Limpando build anterior...
if exist "%PROJECT_ROOT%build" (
    rmdir /s /q "%PROJECT_ROOT%build"
)

mkdir "%BUILD_DIR%"

echo Localizando arquivos Java...

set "SOURCES="

for %%F in ("%SOURCE_DIR%\*.java") do (
    set "SOURCES=!SOURCES! "%%~fF""
)

if "!SOURCES!"=="" (
    echo.
    echo ERRO: nenhum arquivo .java foi encontrado.
    exit /b 1
)

echo Compilando arquivos Java...

javac -encoding UTF-8 -d "%BUILD_DIR%" !SOURCES!

if errorlevel 1 (
    echo.
    echo ERRO: falha na compilacao.
    exit /b 1
)

echo Copiando recursos...

if exist "%RESOURCE_DIR%" (
    xcopy "%RESOURCE_DIR%\*" "%BUILD_DIR%\" /E /I /Y >nul
)

echo.
echo Build concluido com sucesso.
echo Saida: %BUILD_DIR%

endlocal