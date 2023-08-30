FROM maven:3.8.4-openjdk-11-slim as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src/

RUN mvn clean install -Pbuild-jar

FROM flink:1.17.0
ARG JAR_FILE=/app/target/cep-*.jar
RUN mkdir -p $FLINK_HOME/usrlib

COPY --from=builder ${JAR_FILE}  $FLINK_HOME/usrlib/my-flink-job.jar

RUN mkdir -p $FLINK_HOME/plugins/flink-s3-fs-presto
