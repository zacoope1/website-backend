BACKEND_PATH='/mnt/dev1/development/microservice/'

screen -S website-backend -X quit
echo 'Terminating current build'

sudo rm -rf ${BACKEND_PATH}/website-backend-old
echo 'Removing backup.'

sudo cp -r ${BACKEND_PATH}/website-backend ${BACKEND_PATH}/website-backend-old
echo 'Making new backup.'

sudo rm -rf ${BACKEND_PATH}/website-backend
echo 'Removing current build.'

sudo cp -r website-backend/ ${BACKEND_PATH}
echo 'Copying new build to current build directory.'

screen -S website-backend
echo 'Created screen session'

export JAVA_HOME="/usr/lib/jvm/java-14-openjdk-amd64"
echo 'Setting JAVA_HOME to JAVA 14.'

mvn -version

cd ${BACKEND_PATH}/website-backend

mvn clean install
echo 'Running maven clean install.'

chmod +x mvnw

./mvnw spring-boot:run
echo 'Running backend.'

echo 'DONE'