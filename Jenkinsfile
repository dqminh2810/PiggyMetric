pipeline {
    agent {label "jenkins-agent"}

    stages {
        stage('Check env') {
            steps {
                 echo "Node name is $NODE_NAME"
                 echo "Job name is $JOB_NAME"
                 java --version
                 mvn --version
            }
        }
        stage('Build Maven project') {
                    steps {
                         mvn clean package -DskipTests
                    }
                }
    }
}
v