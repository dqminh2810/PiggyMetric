pipeline {
    agent any
    environment {
        DOCKER_COMPOSE_FILE_DIR = '/data/workspace/PiggyMetrics'
        NAMESPACE = 'piggy-metric-dqminh2810'
    }
    stages {
        stage('Test varibale environment') {
            steps {
                 echo "${PROD_ENV}"
            }
        }
        stage('Prepare Environment with Okteto') {
            steps {
                withCredentials([string(credentialsId: 'okteto-token', variable: 'OKTETO_TOKEN')]) {
//                     cleanWs ()
                    sh '''
                    okteto login --token ${OKTETO_TOKEN}
                    okteto namespace ${NAMESPACE}
                    '''
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
