#!/bin/sh
set -e
MVNW_REPOURL=https://repo.maven.apache.org/maven2
WRAPPER_DIR="$(cd "$(dirname "$0")"; pwd)"
MVNW_HOME="$WRAPPER_DIR/.mvn/wrapper"
MVNW_JAR="$MVNW_HOME/maven-wrapper.jar"
MVNW_PROPS="$MVNW_HOME/maven-wrapper.properties"
if [ ! -f "$MVNW_JAR" ]; then
  echo "Missing Maven Wrapper JAR. You need to add it manually or run: mvn -N io.takari:maven:wrapper"
  exit 1
fi
exec java -jar "$MVNW_JAR" "$@"