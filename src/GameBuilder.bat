@echo off
title GameBuilder
md GAME_TITLE
javac -cp thePackage.jar workspace/*.java
jar cfvm GAME_TITLE.jar Manifest.txt thePackage.jar workspace/*.class images/* sounds/*
move GAME_TITLE.jar GAME_TITLE
copy thePackage.jar GAME_TITLE
cd GAME_TITLE
attrib +h thePackage.jar
pause
