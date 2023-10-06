pipeline {

    agent any

    environment {
        GIT_PROJECT = 'git@github.com:dqminh2810/PiggyMetrics.git'
        DOCKER_COMPOSE_FILE_DIR = '/var/jenkins_home/workspace/PiggyMetrics'
        GIT_REPO_NAME = 'PiggyMetrics'
        NAMESPACE = 'piggy-metric-dqminh2810'
    }

    stages {

        stage('Prepare Environment with Okteto') {
            steps {
                withCredentials([string(credentialsId: 'okteto-token', variable: 'OKTETO_TOKEN')]) {
                    cleanWs ()
                    sh '''
                    okteto login --token ${OKTETO_TOKEN}
                    '''
                }
            }
        }

        stage('Build & deploy docker containers to okteto') {
            steps {
                withCredentials([string(credentialsId: 'okteto-token', variable: 'OKTETO_TOKEN')]) {
                    sh '''
                    cd "${DOCKER_COMPOSE_FILE_DIR}"
                    okteto login --token ${OKTETO_TOKEN}
                    okteto namespace ${NAMESPACE}
                    docker comose up --detach
                    '''
                }
            }
        }

    }
}