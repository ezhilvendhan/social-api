#!/bin/sh

usage() {
    echo "Usage: `basename $0` [-b] [-a [dev|prod]] [-t] [-s]"
    echo "  -b      Unit test, Build and package the app"
    echo "  -a      Run the app in the relevant environment"
    echo "     dev  Run app in Dev Environment. Uses run/application-dev.properties"
    echo "     prod Run app in Prod Environment. Uses run/application-prod.properties"
}

build() {
    mvn package && cp target/social-api-*.jar run/
}

app() {
    if [ \( "$ENV" != "dev" \) -a \( "$ENV" != "prod" \) ]; then
        echo $ENV
        usage
        exit 0
    fi
    if [ ! -f run/social-api-*.jar ]; then
        build
    fi
    java -jar run/social-api-*.jar --spring.config.location=run/application-$ENV.properties
}

while getopts "ba:tsh" opt; do
  case $opt in
    b)
      build
      ;;
    a)
      ENV="${OPTARG}"
      app
      ;;
    h)
      usage
      ;;
    \?)
      usage
      ;;
  esac
done