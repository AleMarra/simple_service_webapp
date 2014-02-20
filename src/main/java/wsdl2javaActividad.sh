#!/bin/sh
rm wtp/activity/fiuba/taller/actividad/Actividad*
#./wsdlChangePort.sh Actividad.wsdl
wsdl2java.sh --noBuildXML -S wtp/activity -R wtp/activity -uri Actividad.wsdl
cd wtp/activity/fiuba/taller/actividad/
sed -i 's/package fiuba\.taller\.actividad;/package wtp\.activity\.fiuba\.taller\.actividad;/g' Actividad*
sed -i 's/fiuba\.taller\.actividad\.//g' Actividad*

