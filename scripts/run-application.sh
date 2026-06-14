#!/bin/bash
# shellcheck disable=SC2164
cd ~/workspace/Twilight.org/;

./gradlew clean
./gradlew bootRun --info --debug-jvm

