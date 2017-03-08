#!/bin/bash

set -xe

pwd
env

cf api $cf-api --skip-ssl-validation

cf login -u $cf-username -p $cf-password -o "$cf-org" -s "$cf-space"

cf apps

set +e


echo "Running cf tests..."
wget http://java-$cf-app-suffix.$cf-app-domain/$cf-test-url-endpoint
