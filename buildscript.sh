MICROSERVICES_PATH='/mnt/dev1/development/microservice/'

sudo -u jenkins screen -S website_backend -X quit
echo 'Terminating current build'

sudo rm -rf ${MICROSERVICES_PATH}website-backend-old
echo 'Removing backup.'

sudo cp -r ${MICROSERVICES_PATH}website-backend ${MICROSERVICES_PATH}website-backend-old
echo 'Making new backup.'

sudo rm -rf ${MICROSERVICES_PATH}website-backend
echo 'Removing current build.'

sudo cp -r /var/lib/jenkins/workspace/Back-End-Microservice ${MICROSERVICES_PATH}/website-backend
echo 'Copying new build to current build directory.'

sudo rm ${KEY_PATH}/zacharycooper-ssl.p12
echo 'deleting old ssl key'

cd ${MICROSERVICES_PATH}
echo 'moving into $(pwd)'

sudo chown -R jenkins website-backend/
sudo chmod 777 website-backend/
sudo chmod +x website-backend/mvnw
sudo chmod +x website-backend/mvnw.cmd
echo 'setting permissions to directory'

sudo rm ${MICROSERVICES_PATH}website-backend/src/main/resources/application.properties
sudo cp ${MICROSERVICES_PATH}configs/application.properties ${MICROSERVICES_PATH}website-backend/src/main/resources/
${MICROSERVICES_PATH}configs/keyupdate.sh
echo 'Replacing application.properties file and copying ssl key'


cd website-backend/
echo 'moving into $(pwd)'

sudo -u jenkins screen -dmS website_backend bash -c 'clear && export JAVA_HOME="/usr/lib/jvm/java-14-openjdk-amd64" && cd /mnt/dev1/development/microservice/website-backend && mvn clean install && ./mvnw spring-boot:run; exec sh'
echo 'Started service as a screen under user jenkins'