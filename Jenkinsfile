//@Library('test-job-agent-2-libs') _

pipeline {
    //agent { label 'built-in' }
    agent {label "agent"}

    environment {
        GITHUB_ORGANIZATION = "dqminh2810"
        GITHUB_REPO = "PiggyMetric"
        GITHUB_CREDENTIAL_ID = "github-api"
        WORKSPACE = "${env.WORKSPACE}"
        IMAGE_NAME_MS_CONFIG = 'dqminh2810/hello-world-piggy_config'
        IMAGE_TAG = "${env.BUILD_NUMBER}-${env.GIT_COMMIT?.take(7) ?: 'dev'}"
        DOCKER_CREDENTIALS_ID = 'docker-repository-credential'
        //KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-creds'
    }

    stages {
        stage('Check ENV') {
            steps {
                sh '''
                    echo "-------ENV VARIABLES-------"
                    echo "NODE_NAME is $NODE_NAME"
                    echo "JOB_NAME is $JOB_NAME"
                    echo "IMAGE_NAME_MS_CONFIG is IMAGE_NAME_MS_CONFIG"
                    echo "IMAGE_NAME_MS_EXPERIENCE is IMAGE_NAME_MS_EXPERIENCE"
                    echo "IMAGE_TAG is $IMAGE_TAG"
                    echo "USER is $USER"
                    echo "WORKSPACE is ${WORKSPACE}"
                    echo "-------BUILD TOOLS-------"
                    hostname -I
                    java --version
                    mvn --version
                    docker info
                '''
            }
        }

        stage('Build maven') {
            steps {
                sh '''
                    echo "Building maven..."
                    mvn clean package -DskipTests
                '''
            }
        }
        stage('Build Docker Image') {
          steps {
            script {
              dockerImageMsConfig = docker.build("${IMAGE_NAME_MS_CONFIG}:${IMAGE_TAG}", "${WORKSPACE}/config")
            }
          }
        }

        stage('Push Docker Image') {
          steps {
            script {
              docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                dockerImageMsConfig.push()
              }
            }
          }
        }

        stage('Manual Approval to Deploy') {
            steps {
                script {
                    timeout(time: 5, unit: 'MINUTES') { // Optional: Add a timeout for the approval
                        userInput = input(
                            message: 'Do you approve this deployment?',
                            ok: 'Proceed with Deployment',
                            parameters: [
                                string(name: 'reason', defaultValue: 'No specific reason', description: 'Reason for approval/rejection')
                            ]
                        )
                        // You can use the userInput variable if you defined parameters
                        echo "Approval received with reason: ${userInput.reason}"
                    }
                }
            }
        }

        stage('K3S Deployment') {
            agent {
                kubernetes {
                    cloud 'k3s'
                    defaultContainer 'kubectl'
                    yamlFile 'k8s/kubectl-pod.yaml'
                }
            }
            stages {
                stage('Deploy to K3S') {
                    steps {
                        container('kubectl') {
                            sh '''
                                sed -e "s|__IMAGE_PLACEHOLDER__|${IMAGE_NAME_MS_CONFIG}:${IMAGE_TAG}|g" \
                                k8s/ms-pod.tmpl.yaml > k8s/ms-pod.yaml
                                kubectl --namespace jenkins-ns apply -f k8s/ms-pod.yaml
                            '''
                        }
                    }
                }

                stage('Wait and Check Test Result') {
                    steps {
                        container('kubectl') {
                            sh '''
                                kubectl --namespace jenkins-ns wait --for=condition=Ready pod/hello-world-piggy-ms-pod --timeout=300s || exit 1
                                kubectl --namespace jenkins-ns logs pod/hello-world-piggy-ms-pod
                            '''
                        }
                    }
                }

//                 stage('Clean up') {
//                     steps {
//                         container('kubectl') {
//                             sh '''
//                                 kubectl --namespace jenkins-ns delete pod hello-world-piggy-ms-pod --ignore-not-found=true
//                             '''
//                         }
//                     }
//                 }
            }
        }
    }
}
