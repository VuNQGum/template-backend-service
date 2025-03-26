#!/bin/bash
#echo "Current User:"$(whoami);
awk -v srch="{MODE}" -v repl="${MODE}" '{ sub(srch,repl) }1' app/resources/application.properties > app/resources/application.properties.swp && mv app/resources/application.properties.swp app/resources/application.properties
awk -v srch="{DB_URL}" -v repl="${DB_URL}" '{ sub(srch,repl) }1' app/resources/application.properties > app/resources/application.properties.swp && mv app/resources/application.properties.swp app/resources/application.properties
awk -v srch="{DB_USER}" -v repl="${DB_USER}" '{ sub(srch,repl) }1' app/resources/application.properties > app/resources/application.properties.swp && mv app/resources/application.properties.swp app/resources/application.properties
awk -v srch="{DB_PWD}" -v repl="${DB_PWD}" '{ sub(srch,repl) }1' app/resources/application.properties > app/resources/application.properties.swp && mv app/resources/application.properties.swp app/resources/application.properties
awk -v srch="{UTILS_URL}" -v repl="${UTILS_URL}" '{ sub(srch,repl) }1' app/resources/application.properties > app/resources/application.properties.swp && mv app/resources/application.properties.swp app/resources/application.properties
sed -i "s/{SERVER_KAFKA_NAME}/${SERVER_KAFKA_NAME}/g" app/resources/application.properties
sed -i "s/{RIGHT_TOPIC_NAME}/${RIGHT_TOPIC_NAME}/g" app/resources/application.properties
sed -i "s/{ROLE_TOPIC_NAME}/${ROLE_TOPIC_NAME}/g" app/resources/application.properties
sed -i "s/{USER_TOPIC_NAME}/${USER_TOPIC_NAME}/g" app/resources/application.properties
sed -i "s/{ROLE_USER_TOPIC_NAME}/${ROLE_USER_TOPIC_NAME}/g" app/resources/application.properties
sed -i "s/{ROLE_RIGHT_TOPIC_NAME}/${ROLE_RIGHT_TOPIC_NAME}/g" app/resources/application.properties
awk -v srch="{PUBLICKEY}" -v repl="${PUBLICKEY}" '{ sub(srch,repl) }1' app/resources/application.properties > app/resources/application.properties.swp && mv app/resources/application.properties.swp app/resources/application.properties
exec java $JAVA_OPTS -cp $( cat /app/jib-classpath-file ) $( cat /app/jib-main-class-file )