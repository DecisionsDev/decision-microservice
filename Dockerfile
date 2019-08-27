FROM maven:3.6.1-ibmjava as builder

ARG ODMBUILD
ARG ODMLIB

ENV ODMBUILD=${ODMBUILD} ODMLIB=${ODMLIB}

COPY . .
RUN ./build.sh



FROM ibmjava:8-jre as executable

ARG ODMBUILD
ARG ODMLIB

WORKDIR generation

COPY --from=builder ${ODMLIB}/j2ee_connector-1_5-fr.jar /odmlib/
COPY --from=builder ${ODMLIB}/jrules-engine.jar /odmlib/
COPY --from=builder ${ODMLIB}/jrules-res-execution.jar /odmlib/
COPY --from=builder ${ODMLIB}/jrules-res-manage-tools.jar /odmlib/

COPY --from=builder decisionMicroservice/decisionMicroserviceClient/target/decisionMicroserviceClient.jar /artifact/

ENV ODMLIB /odmlib/

ENTRYPOINT ["java","-jar","/artifact/decisionMicroserviceClient.jar"]
