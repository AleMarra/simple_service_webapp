#!/bin/sh
rm wtp/activity/fiuba/taller/actividad/Actividad*
wsdl2java.sh -S wtp/activity -R wtp/activity -uri Actividad.wsdl
mv -v build.xml wtp/activity/
cd wtp/activity/fiuba/taller/actividad/
sed -i 's/package fiuba\.taller\.actividad;/package wtp\.activity\.fiuba\.taller\.actividad;/g' Actividad*
sed -i 's/fiuba\.taller\.actividad\.//g' Actividad*

