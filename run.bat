@echo off
setlocal

set "PROJECT_ROOT=%~dp0"
set "BUILD_DIR=%PROJECT_ROOT%build\classes"

if not exist "%BUILD_DIR%\TelaInicial.class" (
    echo Build nao encontrado. Compilando primeiro...
    call "%PROJECT_ROOT%build.bat"

    if errorlevel 1 (
        echo Nao foi possivel iniciar o jogo.
        exit /b 1
    )
)

echo Iniciando Geometric Expansion...
java -cp "%BUILD_DIR%" TelaInicial

endlocal