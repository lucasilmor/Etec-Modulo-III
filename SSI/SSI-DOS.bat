@echo off
cls
:menu
cls
color 2

date/t

echo Maquina: %computetrname%           Usuario: %username%

echo            Menu de tarefas
echo =======================================
echo * 1- Abrir calculadora                 *
echo * 2- Mostrar versao do windows
echo * 3- Acessar o melhor canal do mundo!!!
echo * 4- Desligar o pc
echo * 5- Cancelar desligamento do pc
echo * 6- Sair
echo ---------------------------------------


set/p opcao= Escolha uma opcao:
echo -------------------------------
if %opcao% equ 1 goto opcao1
if %opcao% equ 2 goto opcao2
if %opcao% equ 3 goto opcao3
if %opcao% equ 4 goto opcao4
if %opcao% equ 5 goto opcao5
if %opcao% equ 6 goto opcao6

:opcao1
cls
start calc
echo ===========================
echo     Calculadora aberta
echo ===========================
pause
goto menu
 
:opcao2
cls
echo ===========================
echo      Ta ai sua versao
echo ===========================
VER
pause
goto menu

:opcao3
cls
echo =============================================================
echo  Aproveita pra da uns KAPA NO freefire 
echo =============================================================
start https://www.youtube.com/channel/UCHO9_1TAZUYW4oAfrbBBnTQ
pause
goto menu


:opcao4
cls
echo ==============================================
echo     Sua maquina sera desligada em uma hora
echo ==============================================
shutdown -s -t 3600
pause
goto menu

:opcao5
cls
echo =======================
echo     Missao abortada
echo =======================
shutdown -a
pause
goto menu


:opcao6
cls
exit


:opcao7
echo ================================
echo    opcao invalida tente outra
echo ================================
pause
goto menu
