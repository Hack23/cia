#!/bin/bash

TARGET="../../../target/cloudformation-doc"
mkdir -p $TARGET

cat ../resources/cia-dist-cloudformation.json | /usr/local/bin/cfn-graph   | dot -Tpng -o$TARGET/cia-dist-cloudformation.png

exit 0