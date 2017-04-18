#!/bin/bash
echo "============= push docker image ====================================="
echo ${TRAVIS_BRANCH}
echo "====================================================================="
export BRANCH=$(if [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
    echo $TRAVIS_BRANCH; else
    echo $TRAVIS_PULL_REQUEST_BRANCH;
    fi)
echo "====================================================================="
if [ "$BRANCH" == "master" ] || [ "$BRANCH" == "addEvent" ]; then
  docker login --username="$DOCKER_USERNAME" --password="$DOCKER_PASSWORD";
  docker push reactivesw/product;
fi
echo "============= end push docker image ================================="