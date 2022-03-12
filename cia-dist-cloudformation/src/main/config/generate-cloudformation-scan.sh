#!/bin/bash

TARGET="../../../target/cloudformation-nag-reports"
mkdir -p $TARGET

/usr/local/bin/cfn_nag_scan --input-path ../resources/cia-dist-cloudformation.json > $TARGET/cia-dist-cloudformation.json-nag-scan-report.txt
