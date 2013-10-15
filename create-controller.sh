#!/usr/bin/bash
# An attempt of a controller generator script with path, response type,
# request type. Ideally, should cover what every basic Grails template covers:
# list, create, save, show, edit, update and delete, adapted to our models

usage () {
	echo -e "\
Usage: [OPTION]... <EntityName>
Creates a controller for <EntityName> with list, create, save, show, edit,
update and delete services.

  -r, --raw           Creates a controller without any of the available
                      options
  -l, --list          Creates a controller with the list service (@todo)
  -c, --create        Creates a controller with the create service (@todo)
  -sa, --save         Creates a controller with the save service (@todo)
  -sh, --show         Creates a controller with the show service (@todo)
  -e, --edit          Creates a controller with the edit service (@todo)
  -u, --update        Creates a controller with the update service (@todo)
  -d, --delete        Creates a controller with the delete service (@todo)

@todo: define convention path and default services
"
	exit 1
}

if [ $# -eq 0 ];  then
    usage
fi


ENTITY="$1"
ENTITY_LOWERCASE="${ENTITY,,}"
CLASSNAME="${ENTITY}Controller"
FILENAME="${CLASSNAME}.java"
PACKAGE_BASE="com.fiuba.taller"

PACKAGE="$PACKAGE_BASE.controller.$ENTITY_LOWERCASE"
MODEL_PACKAGE="$PACKAGE_BASE.model.$ENTITY_LOWERCASE"
DEPENDENCIES=("javax.ws.rs.GET"\
              "javax.ws.rs.POST"\
              "javax.ws.rs.Path"\
              "javax.ws.rs.PathParam"\
              "javax.ws.rs.Produces"\
              "javax.ws.rs.QueryParam"\
              "javax.ws.rs.core.MediaType")

CLASS_URL="/$ENTITY_LOWERCASE"
PRODUCES="MediaType.APPLICATION_JSON"

# Template strings. Yeah, hardcoded here, kb

LIST_DEPENDENCIES=("java.util.List"\
				   "java.util.ArrayList" )

LIST="\
    @GET
    @Path(\"list\")
    public List<${ENTITY}> list(@QueryParam(\"max\") Integer max) {
        Integer qty = max != null? max : 5;
        // Ws call here?
        List< ${ENTITY} > list = new ArrayList< ${ENTITY} >();
        return list.subList(0, qty);
    }"

puts () {
	echo -e "$1" >> $FILENAME
}

# Actual template creation
> $FILENAME
puts "package ${PACKAGE};"
puts ""
puts "import $MODEL_PACKAGE;"
puts ""
for dep in "${DEPENDENCIES[@]}"
do
	puts "import $dep;"
done
puts ""
puts "@Path(\"${CLASS_URL}\")"
puts "@Produces($PRODUCES)"
puts "public class $CLASSNAME {"
puts ""
puts "}"