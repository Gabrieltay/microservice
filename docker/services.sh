#!/usr/bin/env bash
set -euo pipefail

export MYSQL_HOST="localhost"

function cleanup() {
    docker-compose -f docker/services.yml down
}
trap cleanup EXIT

docker-compose -f docker/services.yml up
