#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/springreactordemo-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} spring-reactor-demo.jar
#ENTRYPOINT ["java","-jar","/spring-reactor-demo.jar"]

#FROM openjdk:8-jdk-alpine
##RUN MKDIR /tmp
##RUN cd /tmp
##COPY spring-reactor-demo.jar
#ARG JAR_FILE=springreactordemo-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} spring-reactor-demo.jar
#ENTRYPOINT ["java","-jar","springreactordemo-0.0.1-SNAPSHOT.jar"]
#EXPOSE 8080



#FROM openjdk:8-jdk-alpine
## ----
## Install Maven
#RUN apk add --no-cache curl tar bash
#ARG MAVEN_VERSION=3.3.9
#ARG USER_HOME_DIR="/root"
#RUN mkdir -p /usr/share/maven && \
#curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
#ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
#ENV MAVEN_HOME /usr/share/maven
#ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
## speed up Maven JVM a bit
#ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
#ENTRYPOINT ["/usr/bin/mvn"]
## ----
## Install project dependencies and keep sources
## make source folder
#RUN mkdir -p /usr/src/app
#WORKDIR /usr/src/app
## install maven dependency packages (keep in image)
#COPY pom.xml /usr/src/app
#RUN mvn -T 1C install && rm -rf target
## copy other source files (keep in image)
#COPY src /usr/src/app/src



FROM maven:3.3-jdk-8-onbuild
RUN echo "PWD is: $PWD"
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/app/target/springreactordemo-0.0.1-SNAPSHOT.jar"]
