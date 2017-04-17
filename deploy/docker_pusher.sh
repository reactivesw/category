#!/bin/bash
echo "------------------ push docker image -----------------"
echo ${TRAVIS_BRANCH}
if [ "$TRAVIS_BRANCH" == "master" ] || [ "$TRAVIS_BRANCH" == "addEvent" ]; then
  docker login --username="$DOCKER_USERNAME" --password="$DOCKER_PASSWORD";
  docker push reactivesw/category;
fi
echo "------------------ end push docker image -------------"