pipeline {
    agent {label "jenkins-agent"}

    environment {
        // Ensure these are defined if not provided by plugins
        GITHUB_ORGANIZATION = "dqminh2810"
        GITHUB_REPO = "PiggyMetric"
    }

    stages {
        stage('Build') {
            steps {
                // Example: Checkout code to ensure GIT_COMMIT is available
                git branch: 'main', credentialsId: 'github-api', url: "https://github.com/${env.GITHUB_ORGANIZATION}/${env.GITHUB_REPO}.git"
                sh 'echo "Running build..."'
                // ... your build steps ...
            }
            post {
                success {
                    script { // <--- Wrap in a script block
                        withCredentials([string(credentialsId: 'github-api', variable: 'GITHUB_TOKEN')]) {
                            sh """
                                curl -X POST -H "Authorization: token $GITHUB_TOKEN" \\
                                -H "Accept: application/vnd.github.v3+json" \\
                                "https://api.github.com/repos/${env.GITHUB_ORGANIZATION}/${env.GITHUB_REPO}/statuses/${env.GIT_COMMIT}" \\
                                -d '{"state": "\'SUCCESS\''", "target_url": "${env.BUILD_URL}", "description": "\'Build successful!\''", "context": "\'Jenkins Build\'"}'
                            """
                        }
                    }
                }
                failure {
                    script { // <--- Wrap in a script block
                        withCredentials([string(credentialsId: 'github-api', variable: 'GITHUB_TOKEN')]) {
                            sh """
                                curl -X POST -H "Authorization: token $GITHUB_TOKEN" \\
                                -H "Accept: application/vnd.github.v3+json" \\
                                "https://api.github.com/repos/${env.GITHUB_ORGANIZATION}/${env.GITHUB_REPO}/statuses/${env.GIT_COMMIT}" \\
                                -d '{"state": "\'FAILURE\''", "target_url": "${env.BUILD_URL}", "description": "\'Build failed!\''", "context": "\'Jenkins Build\'"}'
                            """
                        }
                    }
                }
            }
        }
    }
}

// Define the function within the Jenkinsfile, but outside the 'pipeline' block itself.
// It needs to be a global function or part of a 'script' block.
// For a Declarative Pipeline, defining it outside the 'pipeline' block is fine for simplicity.
// When called from within a 'script' block, the Groovy interpreter handles it.
// void updateGithubCommitStatus(String status, String context, String message, String targetUrl, String commit) {
//     withCredentials([string(credentialsId: 'github-api', variable: 'GITHUB_TOKEN')]) {
//         sh """
//             curl -X POST -H "Authorization: token $GITHUB_TOKEN" \\
//             -H "Accept: application/vnd.github.v3+json" \\
//             "https://api.github.com/repos/${env.GITHUB_ORGANIZATION}/${env.GITHUB_REPO}/statuses/${commit}" \\
//             -d '{"state": "${status.toLowerCase()}", "target_url": "${targetUrl}", "description": "${message}", "context": "${context}"}'
//         """
//     }
// }