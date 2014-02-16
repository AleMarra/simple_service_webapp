#!/bin/sh
rm wtp/loginapihelper/wtp/LoginAPIHelper*
./wsdlChangePort.sh LoginAPIHelper.wsdl
wsdl2java.sh --noBuildXML -S wtp/loginapihelper -R wtp/loginapihelper -uri LoginAPIHelper.wsdl
cd wtp/loginapihelper/wtp/
sed -i 's/package wtp;/package wtp\.loginapihelper\.wtp;/g' LoginAPIHelper*
sed -i 's/wtp\.LoginAPIHelper/LoginAPIHelper/g' LoginAPIHelper*

