#!/bin/sh
arg1=$1
##directory where jar file is located    
dir=build/libs
##jar file name
jar_name=ParkingLot-0.0.1-SNAPSHOT.jar

./gradlew clean build 

java -jar $dir/$jar_name $arg1