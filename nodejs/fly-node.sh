#!/bin/bash

fly -t aws set-pipeline -c ci/pipeline.yml -p canary-node-haas-107 -l ../ci/config.yml
