#!/bin/bash
export CLASSPATH=${CLASSPATH}:${TESTDIR}/json-java-20231013.jar:${TESTDIR}/javax.json-1.0.jar:.
echo $CLASSPATH
rm *.class
javac *.java
