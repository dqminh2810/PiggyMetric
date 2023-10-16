pipeline {
    agent any
    environment {
        DOCKER_COMPOSE_FILE_DIR = '/data/workspace/PiggyMetrics'
        DEV_NAMESPACE = 'piggy-metric-dev-dqminh2810'
        PROD_NAMESPACE = 'piggy-metric-prod-dqminh2810'
    }
    stages {
        stage('Test varibale environment') {
            steps {
                 echo "${PROD_ENV}"
            }
        }
        stage('Prepare Environment with Okteto') {
            steps {
                // cleanWs ()
                withCredentials([string(credentialsId: 'okteto-token', variable: 'OKTETO_TOKEN')]) {
                    sh '''
                    okteto login --token ${OKTETO_TOKEN}
                    '''
                }
                script {
                    if (params.PROD_ENV) {
                        okteto login --token ${OKTETO_TOKEN}
                        okteto namespace ${PROD_NAMESPACE}
                   } else {
                        okteto login --token ${OKTETO_TOKEN}
                        okteto namespace ${DEV_NAMESPACE}
                   }
               }
            }
        }
        stage('Build & deploy docker containers to okteto') {
            steps {
                withCredentials([string(credentialsId: 'okteto-token', variable: 'OKTETO_TOKEN')]) {
                    sh '''
                    cd "${DOCKER_COMPOSE_FILE_DIR}"
                    okteto deploy --build --wait
                    '''
                }
            }
        }
    }
}
