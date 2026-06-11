#!/bin/bash
# shellcheck disable=SC2164
cd ~/workspace/Twilight.org/src/main/java/com/twilight/apis/ || exit 1
echo -e "--------------------------------Dumping API files--------------------------------------"
for file in *;
do
  echo -e "\n<------Dumping file ------->\n\n"
  echo -e "-----------------------------"
  echo -e "$file"
  echo -e "-----------------------------"
  cat "$file" || exit 1
done
# shellcheck disable=SC2103
cd .. || exit 1
cd dataTransferObjects/ || exit 1

echo -e "--------------------------------Dumping Data Transfer Objects files--------------------"
for file in *
do
  echo -e "\n<------Dumping file ------->\n\n"
    echo -e "-----------------------------"
    echo -e "$file"
    echo -e "-----------------------------"
  cat "$file" || exit 1
done

echo -e "-------------------------------Dumping Complete ---------------------------------------"


