#!/bin/bash
# shellcheck disable=SC2164
cd ~/workspace/Twilight.org/;

if docker ps --format '{{.Names}}' | grep -q "^kafka$" \
   && docker ps --format '{{.Names}}' | grep -q "^redis$" \
   && docker ps --format '{{.Names}}' | grep -q "^minio$"; then
  echo "Docker services are running";
else
  docker compose up -d
  echo "Docker services started successfully";
fi