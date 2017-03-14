#!/bin/bash

set -xe

pwd
env

cf api $cf-api --skip-ssl-validation

cf login -u $cf-username -p $cf-password -o "$cf-org" -s "$cf-space"

export TEST_URL=`cf curl /v2/apps/$(cf app $1 --guid)/env | jq -c .application_env_json.VCAP_APPLICATION.application_uris[0]| sed "s/\"//g"`

set +e

echo "Running cf tests..."

echo $TEST_URL

curl -k https://$TEST_URL/health
