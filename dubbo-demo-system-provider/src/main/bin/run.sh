#!/bin/sh

###  ------------------------------ ###
###  service launcher script        ###
###  ------------------------------ ###

cd `dirname $0`
cd ../
if [ -z "$JAVA_OPS" ]; then
  JAVA_OPS="-Dfile.encoding=utf-8"
  JAVA_OPS="$JAVA_OPS -Dconf.home=$(pwd)/conf"
  JAVA_OPS="$JAVA_OPS -DLogback.configurationFile=$(pwd)/conf/logback.xml"
  JAVA_OPS="$JAVA_OPS -Xms128M -Xmx128M -Xss256K"
fi
java $JAVA_OPS -jar  $(pwd)/lib/dubbo-demo-system-provider-1.0.jar