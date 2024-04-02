@echo off
cls
javac -d ./out src/memo/*.java && cd out && java memo.Main

