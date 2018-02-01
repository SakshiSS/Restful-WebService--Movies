#/bin/sh

CLASSPATH=.:$HOME/./:'lib/*'
export CLASSPATH
javac MoviePickClient.java

java MoviePickClient
