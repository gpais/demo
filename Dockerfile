FROM java:8-jre
COPY example.yml /opt/dropwizard/
COPY target/LetsGetDigital-1.0-SNAPSHOT.jar /opt/dropwizard/
EXPOSE 80
WORKDIR /opt/dropwizard
RUN java -jar LetsGetDigital-1.0-SNAPSHOT.jar db migrate example.yml
CMD ["java", "-jar", "-Done-jar.silent=true", "LetsGetDigital-1.0-SNAPSHOT.jar", "server", "example.yml"]
