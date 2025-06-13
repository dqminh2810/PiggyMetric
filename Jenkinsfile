pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/dqminh2810/PiggyMetric.git',
                credentialsId: 'github-api' // ID of your GitHub PAT credential
            }
        }
        stage('Build') {
            steps {
                // Start pending status
                // Note: 'githubCommitStatus' is deprecated, prefer 'githubChecks'
                // For basic status, 'githubCommitStatus' is still widely used
                // Or use the newer GitHub Checks API plugin for richer integration
                script {
                    if (env.GIT_COMMIT) { // Ensure GIT_COMMIT is available
                        // Using the older githubCommitStatus step
                        // You might need to set up 'context' in your Jenkinsfile if you have multiple checks
                        // Or use the newer 'githubChecks' if you have the GitHub Checks API plugin
                        // (example below shows both, choose one based on your plugin setup)

                        // Option 1: Basic commit status (requires GitHub Plugin)
                        // This relies on the global GitHub server configuration
                        // and the context of the status update.
                        // The context will be the job name by default, but can be specified.
                        // You might need to install 'pipeline-utility-steps' plugin for `currentBuild.rawBuild.getCommitId()`
                        // or ensure `env.GIT_COMMIT` is set correctly.
                        // Using `currentBuild.result` in post section is more reliable for final status.

                        // For sending "pending" status at the start of a stage:
                        sh "curl -X POST -H 'Authorization: token ${env.GITHUB_TOKEN}' -H 'Accept: application/vnd.github.v3+json' https://api.github.com/repos/${env.GIT_URL_1_SLASHLESS}/statuses/${env.GIT_COMMIT} -d '{\"state\": \"pending\", \"target_url\": \"${env.BUILD_URL}\", \"description\": \"Build started\", \"context\": \"Jenkins CI / Build\"}'"
                    }
                }

                // Your actual build commands here
                sh '''
                    echo "Node name is $NODE_NAME"
                    echo "Job name is $JOB_NAME"
                    hostname -I
                    java --version
                    mvn --version
                '''
            }
        }
    }
    post {
        always {
            script {
                if (env.GIT_COMMIT) {
                    def buildStatus = currentBuild.result == 'SUCCESS' ? 'success' : 'failure'
                    def description = currentBuild.result == 'SUCCESS' ? 'Build completed successfully.' : 'Build failed.'

                    // Option 1: Basic commit status (requires GitHub Plugin)
                    sh "curl -X POST -H 'Authorization: token ${env.GITHUB_TOKEN}' -H 'Accept: application/vnd.github.v3+json' https://api.github.com/repos/${env.GIT_URL_1_SLASHLESS}/statuses/${env.GIT_COMMIT} -d '{\"state\": \"${buildStatus}\", \"target_url\": \"${env.BUILD_URL}\", \"description\": \"${description}\", \"context\": \"Jenkins CI / Build\"}'"


                    // Option 2: Using the GitHub Checks API Plugin (more modern, richer UI)
                    // This typically requires a GitHub App setup for better authentication and permissions
                    /*
                    // Requires GitHub Checks API plugin
                    // For a real setup, you'd use a more robust way to get the SCM details and credentials
                    try {
                        githubChecks conclusion: buildStatus,
                            name: 'Jenkins CI / My Pipeline',
                            summary: description,
                            text: "Full build logs: ${env.BUILD_URL}",
                            headSha: env.GIT_COMMIT,
                            status: 'completed'
                    } catch (e) {
                        echo "Could not send GitHub Checks API status: ${e.message}"
                    }
                    */
                }
            }
        }
    }
}