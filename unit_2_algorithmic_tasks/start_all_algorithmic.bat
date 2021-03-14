@ECHO OFF
:menu
pause
CLS
ECHO.
ECHO Menu:
ECHO 1.task_1
ECHO 2.task_2
ECHO 3.task_3
ECHO 4.exit
ECHO.

CHOICE /C 1234 /M "Enter your choice:"

IF ERRORLEVEL 4 GOTO exit
IF ERRORLEVEL 3 GOTO task_3
IF ERRORLEVEL 2 GOTO task_2
IF ERRORLEVEL 1 GOTO task_1

:task_1
cd task_1
call SumOfNumber.bat
cd ..
GOTO menu

:task_2
cd task_2
call OccurrenceOfCharacter.bat
cd ..
GOTO menu

:task_3
cd task_3
call LessonTime.bat
cd ..
GOTO menu

:exit
exit
