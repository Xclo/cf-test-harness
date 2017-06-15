#!/bin/bash

fly -t aws set-pipeline -c ci/pipeline.yml -p canary-java-midwest -l ../ci/config.yml
