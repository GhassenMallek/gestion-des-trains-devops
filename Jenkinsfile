pipeline {
            agent any
            stages {
            
                stage('MVN Clean') {
                    steps {
                         {
                            sh """mvn clean install -Drevision=${env.BUILD_NUMBER}"""
                        }
                    }
                }
                stage('MVN Compile') {
                    steps {
                         {
                            sh """mvn compile -Drevision=${env.BUILD_NUMBER}"""
                        }
                    }
                }
                stage('MVN Test') {
                    steps {
                        {
                            sh """mvn test -Drevision=${env.BUILD_NUMBER}"""
                        }
                    }
                }
                stage('Sonar'){
                    steps{
                       {
                            sh """mvn sonar:sonar -Dsonar.projectKey=ghassen -Drevision=${env.BUILD_NUMBER} -Dsonar.host.url=http://172.10.0.55:9000 -Dsonar.login=e4688b6bdba8398b84fa7e94f008b20c340e18d5"""
                        }
                    }
                }
                stage('Nexus'){
                    steps{
                        {
                            sh """mvn clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.${env.BUILD_NUMBER} -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://172.10.0.55:8081/repository/maven-releases/ -Dfile=target/tpAchatProject-1.${env.BUILD_NUMBER}.jar -Drevision=${env.BUILD_NUMBER}"""
                                }
                            }
                        }
                        stage('Building Docker Image') {
                    steps {
                        {
                            sh 'docker build -t mghassen1998/tpachat .'
                                }
                            }
                        }
                stage('Login to DockerHub') {
                    steps{
                       {
                            sh 'docker login -u mghassen1998 -p Ghassen1998'
                            }
                        }
                    }
                stage('Push to DockerHub') {
                    steps{
                       {
                            sh 'docker push mghassen1998/tpachat'
                             }
                        }
                    }
               {
                    steps{
                       dir('Spring/tpAchatProject'){
                            sh 'docker-compose up -d'
                            }
                       }
                    }
                }
                }
