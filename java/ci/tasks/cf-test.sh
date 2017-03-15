#!/bin/bash

set -xe

pwd
env


cf api $cf_api --skip-ssl-validation

cf login -u $cf_username -p $cf_password -o "$cf_org" -s "$cf_space"

export TEST_URL=`cf curl /v2/apps/$(cf app $1 --guid)/env | jq -c .application_env_json.VCAP_APPLICATION.application_uris[0]| sed "s/\"//g"`

set +e

echo "Running cf tests..."

echo $TEST_URL

curl -k https://$TEST_URL/health
