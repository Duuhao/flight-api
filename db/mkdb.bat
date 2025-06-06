@echo off
setlocal enabledelayedexpansion

REM 设置UTF-8编码
chcp 65001 > nul

REM 数据库配置
set DB_HOST=localhost
set DB_PORT=3306
set DB_NAME=flight_sql
set DB_USER=root
set DB_PASS=password

REM 迁移目录
set DDL_DIR=.\ddl

REM 显示当前配置
echo 数据库配置:
echo 主机: %DB_HOST%
echo 端口: %DB_PORT%
echo 数据库: %DB_NAME%
echo.

echo 开始创建数据库...
for /f "delims=" %%f in ('dir /b %DDL_DIR%\*.sql') do (
    set "filename=%%~nf"
    set "tablename=!filename:create_=!"
    set "tablename=!tablename:_table=!"
    set "tablename=!tablename:.sql=!"
    
    REM 检查表是否已存在
    echo 正在检查表: !tablename!
    mysql -h%DB_HOST% -P%DB_PORT% -u%DB_USER% -p%DB_PASS% %DB_NAME% -N -e "SHOW TABLES LIKE '!tablename!'" > check_table.txt
    set /p table_check=<check_table.txt
    del check_table.txt
    
    if "!table_check!" == "!tablename!" (
        echo 表已存在: !tablename!
    ) else (
        echo 表不存在，准备创建: !tablename!
        echo 执行: %%f
        mysql -h%DB_HOST% -P%DB_PORT% -u%DB_USER% -p%DB_PASS% %DB_NAME% -e "source %DDL_DIR%\%%f"
        if %errorlevel% equ 0 (
            echo 成功创建表: !tablename!
        ) else (
            echo 错误: 执行%%f失败
            type "%DDL_DIR%\%%f"
            pause
            exit /b 1
        )
    )
)

echo 创建数据库完成
pause
