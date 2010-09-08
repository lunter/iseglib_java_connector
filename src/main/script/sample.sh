#!/bin/bash
java -cp `ls jar/*.jar|tr '\n' ':'` com.innovatrics.iseglib.Sample "$@"

