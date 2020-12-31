MICROSERVICES_PATH='/mnt/dev1/development/microservice/'

screen -S website-backend -X quit
echo 'Terminating current build'

sudo rm -rf ${MICROSERVICES_PATH}website-backend-old
echo 'Removing backup.'

sudo cp -r ${MICROSERVICES_PATH}/website-backend ${MICROSERVICES_PATH}website-backend-old
echo 'Making new backup.'

sudo rm -rf ${MICROSERVICES_PATH}website-backend
echo 'Removing current build.'

sudo cp -r website-backend/ ${MICROSERVICES_PATH}
echo 'Copying new build to current build directory.'

sudo rm ${MICROSERVICES_PATH}website-backend/src/main/resources/application.properties
sudo cp ${MICROSERVICES_PATH}configs/application.properties ${MICROSERVICES_PATH}/website-backend/src/main/resources/
echo 'Replacing application.properties file'

screen -dmS website-backend bash -c 'export JAVA_HOME="/usr/lib/jvm/java-14-openjdk-amd64"; mvn-version; mvn clean install; ./mvnw spring-boot:run'
echo 'Created screen session'

# export JAVA_HOME="/usr/lib/jvm/java-14-openjdk-amd64"
# echo 'Setting JAVA_HOME to JAVA 14.'

# mvn -version

# cd ${MICROSERVICES_PATH}website-backend

# mvn clean install
# echo 'Running maven clean install.'

# chmod +x mvnw
# echo 'Setting permissions to mvnw'

# echo 'Booting up backend.'
# ./mvnw spring-boot:run

echo 'Done!'