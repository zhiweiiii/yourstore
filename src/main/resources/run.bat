@echo off
echo 记录灵感
cd target/classes
java com.wade.yourstore.shell.InputJavaShell
set time=于%date:~0,4%年%date:~5,2%月%date:~8,2%日%time:~0,2%点%time:~3,2%分%time:~6,2%秒
echo %time%记录成功

echo 按任意键退出
pause>nul
exit