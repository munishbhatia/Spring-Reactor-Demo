#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/springreactordemo-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} spring-reactor-demo.jar
#ENTRYPOINT ["java","-jar","/spring-reactor-demo.jar"]

FROM openjdk:8-jdk-alpine
#RUN MKDIR /tmp
#RUN cd /tmp
#COPY spring-reactor-demo.jar
ARG JAR_FILE=target/springreactordemo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} spring-reactor-demo.jar
ENTRYPOINT ["java","-jar","springreactordemo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080