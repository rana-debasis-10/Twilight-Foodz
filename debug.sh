#!/bin/bash
# shellcheck disable=SC2164
cd ~/workspace/Twilight.org/scripts/

# shellcheck disable=SC2181
rm dump.txt &>/dev/null
touch dump.txt &>/dev/null

./dump.sh > dump.txt
dump_status=$?
./check.sh "$dump_status" "Dump"

./export.sh
export_status=$?
./check.sh "$export_status" "Export"
