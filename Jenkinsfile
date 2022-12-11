pipeline {
            agent any
            stages {
            
                stage('MVN Clean') {
                    steps {
                            sh """mvn clean install -Drevision=${env.BUILD_NUMBER}"""
                    }
                }
                stage('MVN Compile') {
                    steps {
                            sh """mvn compile -Drevision=${env.BUILD_NUMBER}"""
                    }
                }
                stage('MVN Test') {
                    steps {
                            sh """mvn test -Drevision=${env.BUILD_NUMBER}"""
                    }
                }
                stage('Sonar'){
                    steps{
                            sh """mvn sonar:sonar -Dsonar.projectKey=ghassen -Drevision=${env.BUILD_NUMBER} -Dsonar.host.url=http://172.10.0.55:9000 -Dsonar.login=e4688b6bdba8398b84fa7e94f008b20c340e18d5"""
                    }
                }
                stage('Nexus'){
                    steps{
                      script {
                          pom = readMavenPom file: "./pom.xml";
                          filesByGlob = findFiles(glob: "./target/*.${pom.packaging}");
                          echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                          artifactPath = filesByGlob[0].path;
                          artifactExists = fileExists artifactPath;
                          if(artifactExists) {
                              echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                              nexusArtifactUploader(
                                  nexusVersion: NEXUS_VERSION,
                                  protocol: NEXUS_PROTOCOL,
                                  nexusUrl: NEXUS_URL,
                                  groupId: pom.groupId,
                                  version: pom.version,
                                  repository: NEXUS_REPOSITORY,
                                  credentialsId: NEXUS_CREDENTIAL_ID,
                                  artifacts: [
                                      [artifactId: pom.artifactId,
                                      classifier: '',
                                      file: artifactPath,
                                      type: pom.packaging],
                                      [artifactId: pom.artifactId,
                                      classifier: '',
                                      file: "./pom.xml",
                                      type: "pom"]
                                  ]
                              );
                          } else {
                              error "*** File: ${artifactPath}, could not be found";
                          }
                          }
                            }
                        }
                        stage('Building Docker Image') {
                    steps {
                            sh 'docker build -t mghassen1998/tpachat .'
                            }
                        }
                stage('Login to DockerHub') {
                    steps{
                            sh 'docker login -u mghassen1998 -p Ghassen1998'
                        }
                    }
                stage('Push to DockerHub') {
                    steps{
                            sh 'docker push mghassen1998/tpachat'
                        }
                    }
               {
                    steps{
                            sh 'docker-compose up -d'
                            }
                    }
                }
                }
