#!/bin/bash

export TEST_URL=`cf curl /v2/apps/$(cf app $1 --guid)/env | jq -c .application_env_json.VCAP_APPLICATION.application_uris[0]|  sed "s/\"//g"`


echo $TEST_URL

curl -k https://$TEST_URL/health

