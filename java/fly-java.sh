#!/bin/bash

fly -t aws set-pipeline -c ci/pipeline.yml -p java-test -l ci/config.yml
