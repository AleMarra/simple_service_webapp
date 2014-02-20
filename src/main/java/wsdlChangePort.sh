#!/bin/sh

if [ -z $1 ]; then
	echo "Usage: ./wsdlChangePort.sh AnyFile.wsdl"
	exit 1
fi


sed -i  's/localhost:8080/localhost:58080/g' "$1"