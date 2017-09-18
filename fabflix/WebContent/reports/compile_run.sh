#!/bin/bash

cd /opt/tomcat/webapps/fabflix/source_XML_parsing
sudo javac -classpath ./:lib/mysql-connector-java-5.1.38-bin.jar:lib/javax.json-1.0.jar -d bin *.java
cd bin/
java -classpath .:../lib/mysql-connector-java-5.1.38-bin.jar:../lib/javax.json-1.0.jar fabflix.Main
cd ..



