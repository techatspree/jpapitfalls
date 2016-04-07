FROM jboss/wildfly:10.0.0.Final

COPY config-files /opt/jboss/config-files

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin+2016 --silent

RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/opt/jboss/config-files/configureWildfly.cli

# Fix for Error: Could not rename /opt/jboss/wildfly/standalone/configuration/standalone_xml_history/current

RUN rm -rf /opt/jboss/wildfly/standalone/configuration/standalone_xml_history
RUN chown -R jboss:jboss /opt/jboss/wildfly/
