TIME=$1

if [ -z $TIME ]; then
	TIME=-300
fi

FILES=$(find src -name *.java -mmin $TIME)

if [ -z "$FILES" ]; then
	echo "Nothing modified during the last $TIME minutes"
	exit
fi

scp -rP 2522 $FILES root@vmtaller:/home/presentación/

echo "Done. Don't forget to run the script 'buildAndDeploy.sh' located at vmtaller:/home/presentación"
