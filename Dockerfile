FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/tpAchatProject-1.${env.BUILD_NUMBER}.jar tpAchatProject-1.${env.BUILD_NUMBER}.jar
ENTRYPOINT ["java","-jar","/tpAchatProject-1.${env.BUILD_NUMBER}.jar"]
