mvn package
java -jar target/LetsGetDigital-1.0.0-SNAPSHOT.jar db drop-all --confirm-delete-everything example.yml
java -jar target/LetsGetDigital-1.0.0-SNAPSHOT.jar db migrate example.yml
java -jar target/LetsGetDigital-1.0.0-SNAPSHOT.jar server example.yml
