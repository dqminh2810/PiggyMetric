@Library('test-job-agent-2-libs') _

pipeline {
    agent {label "jenkins-agent"}

    environment {
        GITHUB_ORGANIZATION = "dqminh2810"
        GITHUB_REPO = "PiggyMetric"
        GITHUB_CREDENTIAL_ID = "github-api"
    }

    stages {
        stage('Check ENV') {
            steps {
                sh '''
                    echo "Node name is $NODE_NAME"
                    echo "Job name is $JOB_NAME"
                    hostname -I
                    java --version
                    mvn --version
                '''
            }
            post {
                success {
                    githubStatus(
                        status: 'success',
                        context: 'Jenkins checkout env',
                        message: 'Checkout env successful!',
                        targetUrl: env.BUILD_URL,
                        commit: env.GIT_COMMIT,
                        credentialsId: env.GITHUB_CREDENTIAL_ID,
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
                failure {
                    githubStatus(
                        status: 'failure',
                        context: 'Jenkins checkout env',
                        message: 'Checkout env failed!',
                        targetUrl: env.BUILD_URL,
                        commit: env.GIT_COMMIT,
                        credentialsId: env.GITHUB_CREDENTIAL_ID,
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
            }
        }

        stage('Build maven') {
            steps {
                sh '''
                    echo "Building maven..."
                    mvn clean package -DskipTests
                '''
            }
            post {
                success {
                    githubStatus(
                        status: 'success',
                        context: 'Jenkins build maven',
                        message: 'Build successful!',
                        targetUrl: env.BUILD_URL,
                        commit: env.GIT_COMMIT,
                        credentialsId: env.GITHUB_CREDENTIAL_ID,
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
                failure {
                    githubStatus(
                        status: 'failure',
                        context: 'Jenkins build maven',
                        message: 'Build failed!',
                        targetUrl: env.BUILD_URL,
                        commit: env.GIT_COMMIT,
                        credentialsId: env.GITHUB_CREDENTIAL_ID,
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
            }
        }

        stage('Unit test') {
            steps {
                sh '''
                    echo "Unit testing..."
                    mvn test
                '''
            }
            post {
                success {
                    githubStatus(
                        status: 'success',
                        context: 'Jenkins unit test',
                        message: 'Unit test successful!',
                        targetUrl: env.BUILD_URL,
                        commit: env.GIT_COMMIT,
                        credentialsId: env.GITHUB_CREDENTIAL_ID,
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
                failure {
                    githubStatus(
                        status: 'failure',
                        context: 'Jenkins unit test',
                        message: 'Unit test failed!',
                        targetUrl: env.BUILD_URL,
                        commit: env.GIT_COMMIT,
                        credentialsId: env.GITHUB_CREDENTIAL_ID,
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
            }
        }
    }
}
