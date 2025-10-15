//@Library('test-job-agent-2-libs') _

pipeline {
    agent {label "agent"}

    environment {
        GITHUB_ORGANIZATION = "dqminh2810"
        GITHUB_REPO = "PiggyMetric"
        GITHUB_CREDENTIAL_ID = "github-api"
        GITHUB_BRANCH_NAME = "${env.GIT_BRANCH}"
        WORKSPACE = "${env.WORKSPACE}"
        IMAGE_NAME_MS_CONFIG = 'dqminh2810/hello-world-piggy_config'
//         IMAGE_TAG = "${env.BUILD_NUMBER}-${env.GIT_COMMIT?.take(7) ?: 'dev'}"
        IMAGE_TAG = "arm64"
        DOCKER_CREDENTIALS_ID = 'docker-repository-credential'
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
                    echo "BRANCH_NAME is ${GITHUB_BRANCH_NAME}"
                    echo "-------BUILD TOOLS-------"
                    git rev-parse --abbrev-ref HEAD
                    hostname -I
                    java --version
                    mvn --version
                    docker info
                '''
            }
        }

        stage('Unit Test & Build maven') {
            steps {
                sh '''
                    echo "Branch name is ${env.BRANCH_NAME}"
                    echo "Unit Test & Build maven project..."
                    mvn clean package
                '''
            }
        }

//         stage('Build Docker Image') {
//             steps {
//                 script {
//                     dockerImageMsConfig = docker.build("${IMAGE_NAME_MS_CONFIG}:${IMAGE_TAG}", "${WORKSPACE}/config")
//                 }
//             }
//         }
//
//         stage('Push Docker Image') {
//             steps {
//                 script {
//                     docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
//                     dockerImageMsConfig.push()
//                     }
//                 }
//             }
//         }

        stage('Prepare ENV for K3S deployment') {
            agent {
                kubernetes {
                    cloud 'k3s-ec2'
                    defaultContainer 'kubectl'
                    yamlFile 'k8s/kubectl-pod.yaml'
                }
            }
            stages {
                stage('Integration test on K3S') {
                    steps {
                        container('kubectl') {
                            sh '''
                                sed -e "s|__IMAGE_PLACEHOLDER__|${IMAGE_NAME_MS_CONFIG}:${IMAGE_TAG}|g" \
                                k8s/ms-pod-test.tmpl.yaml > k8s/ms-pod-test.yaml
                                kubectl --namespace jenkins-ns apply -f k8s/ms-pod-test.yaml
                                kubectl --namespace jenkins-ns wait --for=condition=Ready pod/hello-world-piggy-ms-pod-test --timeout=300s || exit 1
                                kubectl --namespace jenkins-ns logs pod/hello-world-piggy-ms-pod-test
                            '''
                        }
                    }
                }

                stage('Clean up Integration test ENV') {
                    steps {
                        container('kubectl') {
                            sh '''
                                kubectl --namespace jenkins-ns delete pod hello-world-piggy-ms-pod-test --ignore-not-found=true
                            '''
                        }
                    }
                }

                stage('Manual Approval to Deploy on Production') {
                    when {
                        branch 'main'
                    }

                    steps {
                        input 'Do you approve deployment?'
                        echo 'Approval received. Deploying to k3s...'
                        container('kubectl') {
                            sh '''
                                kubectl --namespace jenkins-ns apply -f k8s/ms-pod-prod.yaml
                            '''
                        }
                    }
                }
            }
        }
    }
}
