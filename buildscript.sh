MICROSERVICES_PATH='/mnt/dev1/development/microservice/'

screen -S website_backend -X quit
echo 'Terminating current build'

sudo rm -rf ${MICROSERVICES_PATH}website-backend-old
echo 'Removing backup.'

sudo cp -r ${MICROSERVICES_PATH}website-backend ${MICROSERVICES_PATH}website-backend-old
echo 'Making new backup.'

sudo rm -rf ${MICROSERVICES_PATH}website-backend
echo 'Removing current build.'

sudo cp -r /var/lib/jenkins/workspace/Back-End-Microservice ${MICROSERVICES_PATH}/website-backend
echo 'Copying new build to current build directory.'

sudo chmod 777 ${MICROSERVICES_PATH}/website-backend
echo 'setting permissions to directory'

sudo rm ${MICROSERVICES_PATH}website-backend/src/main/resources/application.properties
sudo cp ${MICROSERVICES_PATH}configs/application.properties ${MICROSERVICES_PATH}website-backend/src/main/resources/
echo 'Replacing application.properties file'

# export JAVA_HOME="/usr/lib/jvm/java-14-openjdk-amd64" 
# echo 'Setting JAVA_HOME to JAVA 14.'

# mvn clean install
# echo 'Running maven clean install.'

screen -dmS website_backend bash -c 'export JAVA_HOME="/usr/lib/jvm/java-14-openjdk-amd64" && cd /mnt/dev1/development/microservice/website-backend && mvn clean install && sudo chmod +x mvnw && ./mvnw spring-boot:run; exec sh' && echo 'Setting permissions to mvnw. Created screen session.' && echo 'Done!'
