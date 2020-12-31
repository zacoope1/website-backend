export JAVA_HOME="/usr/lib/jvm/java-14-openjdk-amd64"
cd /mnt/dev1/development/microservice/website-backend
mvn clean install && sudo chmod +x mvnw && ./mvnw spring-boot:run
