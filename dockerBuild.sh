#!/usr/bin/env bash
export DOCKER_BUILDKIT=0
export COMPOSE_DOCKER_CLI_BUILD=0
./gradlew clean build
mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/github-repo-lister-0.0.1-SNAPSHOT.jar)
docker build -t shpap/github-project-lister .