@echo off
echo ��¼���
cd target/classes
java com.wade.yourstore.shell.InputJavaShell
set time=��%date:~0,4%��%date:~5,2%��%date:~8,2%��%time:~0,2%��%time:~3,2%��%time:~6,2%��
echo %time%��¼�ɹ�

echo ��������˳�
pause>nul
exit