pipeline {
  agent any
  environment {
    NEXUS_VERSION = "nexus3"
    NEXUS_PROTOCOL = "http"
    NEXUS_URL = "172.10.0.55:8081"
    NEXUS_REPOSITORY = "maven-releases"
    NEXUS_CREDENTIAL_ID = "Nexus-Creds"
    DOCKER_CREDENTIAL_ID = "Docker-Creds"
    VERSION = "1.${env.BUILD_NUMBER}"
    DOCKER_CREDS = credentials('Docker-Creds')
  }
  stages {

    stage("Maven Clean") {
      steps {
        script {
          sh "mvn -f'./pom.xml' clean -DskipTests=true -Drevision=${VERSION}"
        }
      }
    }
    stage("Maven Compile") {
      steps {
        script {
          sh "mvn -f'./pom.xml' compile -DskipTests=true -Drevision=${VERSION}"
        }
      }
    }
    stage("Maven test") {
      steps {
        script {
          sh "mvn -f'./pom.xml' test -Drevision=${VERSION}"
        }
      }
    }
    stage("Maven Sonarqube") {
      steps {
        script {
          sh "mvn -f'./pom.xml' sonar:sonar -Dsonar.login=admin -Dsonar.password=Admin -Drevision=${VERSION}"
        }
      }
    }
    stage("Maven Build") {
      steps {
        script {
          sh "mvn -f'./pom.xml' package -DskipTests=true -Drevision=${VERSION}"
        }
        echo ":$BUILD_NUMBER"
      }
    }
    stage('Nexus'){
                    steps{
                            sh """mvn clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.${env.BUILD_NUMBER} -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://172.10.0.55:8081/repository/maven-releases/ -Dfile=target/tpAchatProject-1.${env.BUILD_NUMBER}.jar -Drevision=${env.BUILD_NUMBER}"""
                            }
                        }
     stage('Login to DockerHub') {
      steps {
        dir('./') {
          echo DOCKER_CREDS_USR
          sh('docker login -u $DOCKER_CREDS_USR -p $DOCKER_CREDS_PSW')
        }
      }
    }
    stage('Pull the file off Nexus') {
      steps {
        dir('./') {
          withCredentials([usernameColonPassword(credentialsId: 'Nexus-Creds', variable: 'NEXUS_CREDENTIALS')]) {
            echo '$VERSION/tpAchatProject-$VERSION.jar'
            sh script: 'curl -u ${NEXUS_CREDENTIALS} -o ./target/tpAchatProject.jar "$NEXUS_URL/repository/$NEXUS_REPOSITORY/com/esprit/examen/tpAchatProject/$VERSION/tpAchatProject-$VERSION.jar"'
          }
        }
      }
    }
    stage('Building Docker Image Spring') {
      steps {
        dir('./') {
          sh 'docker build -t $DOCKER_CREDS_USR/tpgestiondestrainback .'
        }
      }
    }
    stage('Push to DockerHub (Spring )') {
      steps {
        dir('./') {
          sh 'docker push $DOCKER_CREDS_USR/tpgestiondestrainback'
        }
      }
    }
    stage('Docker Compose') {
      steps {
        dir('./'){
        sh 'docker-compose up -d'
        }
      }
    }
  }

}
