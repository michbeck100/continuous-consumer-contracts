#!/bin/sh
curl -X PUT -H "Content-Type: application/json" -d "{}" http://localhost:9090/pacticipants/movie-catalog-service/versions/$1/tags/prod
