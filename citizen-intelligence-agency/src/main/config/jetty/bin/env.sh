#!/bin/bash  
#
# Startup script for jetty under *nix systems (it works under NT/cygwin too).
export JAVA_HOME=/opt/jdk1.7.0
export JETTY_USER=cia
export JETTY_HOME=/opt/jetty
export JAVA_OPTIONS="-server -Xmx1024m -Xms1024m -XX:+UseParallelGC -XX:MaxPermSize=85m -XX:+AggressiveOpts -XX:+DoEscapeAnalysis"
