# shellcheck disable=SC2034
EXIT_CODE=$1
TASK=$2
if [ "$EXIT_CODE" -eq 0 ]; then
    # shellcheck disable=SC2028
    echo -e "\n$TASK successful";
else
    # shellcheck disable=SC2028
    echo -e "\n$TASK failed";
fi