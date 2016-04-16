#!/bin/bash
echo run this.
#/bin/bash -c -v "java -jar /opt/dropwizard/LetsGetDigital-0.0.1-SNAPSHOT.jar server /opt/dropwizard/example.yml"
#/bin/sh -c "java -jar /opt/dropwizard/LetsGetDigital-0.0.1-SNAPSHOT.jar server /opt/dropwizard/example.yml"
/bin/sh -c "java -jar /opt/dropwizard/LetsGetDigital-0.0.1-SNAPSHOT.jar server /opt/dropwizard/example.yml & logstash-forwarder -config=/etc/logstash-forwarder.conf"