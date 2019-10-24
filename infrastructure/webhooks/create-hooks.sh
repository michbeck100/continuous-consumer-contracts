#!/bin/sh
curl -X POST -H "Content-Type: application/json" -d @movie-info-service.json http://localhost:9090/webhooks/provider/movie-info-service/consumer/movie-catalog-service
curl -X POST -H "Content-Type: application/json" -d @ratings-data-service.json http://localhost:9090/webhooks/provider/ratings-data-service/consumer/movie-catalog-service
