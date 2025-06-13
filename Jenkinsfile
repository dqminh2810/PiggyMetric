pipeline {
    agent any
    stages {
        stage('Build') {
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
                    updateGithubCommitStatus(status: 'SUCCESS', context: 'Jenkins Build', message: 'Build successful!', targetUrl: env.BUILD_URL)
                }
                failure {
                    updateGithubCommitStatus(status: 'FAILURE', context: 'Jenkins Build', message: 'Build failed!', targetUrl: env.BUILD_URL)
                }
            }
        }
    }
}

// Custom step to update GitHub status
void updateGithubCommitStatus(String status, String context, String message, String targetUrl) {
    withCredentials([string(credentialsId: 'github-api', variable: 'GITHUB_TOKEN')]) {
        sh """
            curl -X POST -H "Authorization: token $GITHUB_TOKEN" \
            -H "Accept: application/vnd.github.v3+json" \
            "https://api.github.com/repos/${env.GITHUB_ORGANIZATION}/${env.GITHUB_REPO}/statuses/${env.GITHUB_COMMIT}" \
            -d '{"state": "${status.toLowerCase()}", "target_url": "${targetUrl}", "description": "${message}", "context": "${context}"}'
        """
    }
}