#!/bin/bash
# shellcheck disable=SC2164
cd ~/workspace/Twilight.org/;

./gradlew bootRun --debug -- stacktrace --debug-jvm

