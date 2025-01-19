#start with a base image containing Java runtime
FROM openjdk:17-jdk-slim
#Information around who maintains the image
MAINTAINER easybit.com
# Add the application's jar to the image
COPY target/Accounts-0.0.1-SNAPSHOT.jar Accounts-0.0.1-SNAPSHOT.jar
# execute the application
ENTRYPOINT ["java","-jar","Accounts-0.0.1-SNAPSHOT.jar"]