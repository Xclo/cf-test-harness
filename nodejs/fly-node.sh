#!/bin/bash

fly -t aws set-pipeline -c ci/pipeline.yml -p canary-node-prod -l ../ci/config.yml
