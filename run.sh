#!/bin/bash
clear
# shellcheck disable=SC2164
cd ~/workspace/Twilight.org/scripts/

# shellcheck disable=SC2181
rm dump.txt &>/dev/null
touch dump.txt &>/dev/null

./dump.sh > dump.txt

source ./export.sh

# shellcheck disable=SC2164
./docker-check.sh

# shellcheck disable=SC2164
cd ~/workspace/Twilight.org/scripts/
./run-application.sh
