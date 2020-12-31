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

sudo chown -R jenkins website-backend/

sudo chmod 777 ${MICROSERVICES_PATH}/website-backend
echo 'setting permissions to directory'

sudo rm ${MICROSERVICES_PATH}website-backend/src/main/resources/application.properties
sudo cp ${MICROSERVICES_PATH}configs/application.properties ${MICROSERVICES_PATH}website-backend/src/main/resources/
echo 'Replacing application.properties file'

cd ${MICROSERVICES_PATH}website-backend/

export JAVA_HOME="/usr/lib/jvm/java-14-openjdk-amd64"

mvn clean install && ./mvnw spring-boot:run 