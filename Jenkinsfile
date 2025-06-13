@Library('test-job-agent-2-libs') _

pipeline {
    agent {label "jenkins-agent"}

    environment {
        // Ensure these are defined if not provided by plugins
        GITHUB_ORGANIZATION = "dqminh2810"
        GITHUB_REPO = "PiggyMetric"
    }

    stages {
        stage('Checkout') {
            steps {
                // Example: Checkout code to ensure GIT_COMMIT is available
                git branch: 'main', credentialsId: 'github-api', url: "https://github.com/${env.GITHUB_ORGANIZATION}/${env.GITHUB_REPO}.git"
                sh 'echo "Checkout scm..."'
            }
            post {
                success {
                    githubStatus(
                        status: 'success',
                        context: 'Jenkins checkout scm',
                        message: 'Checkout scm successful!',
                        targetUrl: env.BUILD_URL,
                        commit: env.GIT_COMMIT,
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
                failure {
                    githubStatus(
                        status: 'failure',
                        context: 'Jenkins checkout scm',
                        message: 'Checkout scm failed!',
                        targetUrl: env.BUILD_URL,
                        commit: env.GIT_COMMIT,
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
            }
        }
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
                        githubOrganization: env.GITHUB_ORGANIZATION,
                        githubRepository: env.GITHUB_REPO
                    )
                }
            }
        }
    }
}
