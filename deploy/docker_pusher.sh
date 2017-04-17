#!/bin/bash
if [ "$TRAVIS_BRANCH" == "master" ]; then
  docker login --username="$DOCKER_USERNAME" --password="$DOCKER_PASSWORD";
  docker push reactivesw/category;
fi