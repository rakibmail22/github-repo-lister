#!/usr/bin/env bash

#This script is for demonstration purpose only. This should never be in main repo
docker run -v $(pwd):/data -e "SPRING_PROFILES_ACTIVE=prod" -e "GITHUB_CLIENT_SEARCH_NAME=githubSearchClient" -e "GITHUB_CLIENT_SEARCH_URL=https://api.github.com/search" -p 8080:8080 -t shpap/github-project-lister