#!/bin/bash

fly -t aws set-pipeline -c ci/pipeline.yml -p blue-green-pipeline -l ci/config.yml
