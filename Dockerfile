FROM java:8-jre
COPY example.yml /opt/dropwizard/
COPY target/LetsGetDigital-0.0.1-SNAPSHOT.jar /opt/dropwizard/
EXPOSE 80
WORKDIR /opt/dropwizard
RUN java -jar LetsGetDigital-0.0.1-SNAPSHOT.jar db migrate example.yml
CMD ["java", "-jar", "-Done-jar.silent=true", "LetsGetDigital-0.0.1-SNAPSHOT.jar", "server", "example.yml"]
