//@Library('test-job-agent-2-libs') _

pipeline {
  agent { label 'built-in' }
  //agent {label "jenkins-agent"}

  environment {
    GITHUB_ORGANIZATION = "dqminh2810"
    GITHUB_REPO = "PiggyMetric"
    GITHUB_CREDENTIAL_ID = "github-api"
    IMAGE_NAME = 'dqminh2810/hello-world-piggy_experience-service'
    IMAGE_TAG = "v${env.BUILD_NUMBER}"
    DOCKER_CREDENTIALS_ID = 'docker-repository-credential'
    //KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-creds'
  }

  stages {
    stage('Check ENV') {
        steps {
            sh '''
                echo "Node name is $NODE_NAME"
                echo "Job name is $JOB_NAME"
                echo "IMAGE_NAME is $IMAGE_NAME"
                echo "IMAGE_TAG is $IMAGE_TAG"
                hostname -I
                java --version
                mvn --version
                docker info
                kubectl version
            '''
        }
    }
    stage('Build maven') {
        steps {
            sh '''
                echo "Building maven..."
                mvn clean package -DskipTests
            '''
        }
    }
    stage('Build Docker Image') {
      steps {
        script {
          dockerImage = docker.build("${IMAGE_NAME}:${IMAGE_TAG}")
        }
      }
    }

    stage('Push Docker Image') {
      steps {
        script {
          docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
            dockerImage.push()
          }
        }
      }
    }

    /*
    stage('Deploy to Kubernetes') {
      steps {
        withCredentials([file(credentialsId: KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG')]) {
          script {
            sh '''
              export KUBECONFIG=$KUBECONFIG

              # Replace image tag dynamically and apply manifest
              sed "s|your-dockerhub-username/my-custom-app:latest|${IMAGE_NAME}:${IMAGE_TAG}|" k8s/test-pod.yaml > k8s/test-pod-rendered.yaml

              kubectl apply -f k8s/test-pod-rendered.yaml
            '''
          }
        }
      }
    }

    stage('Wait and Check Test Result') {
      steps {
        withCredentials([file(credentialsId: KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG')]) {
          script {
            sh '''
              export KUBECONFIG=$KUBECONFIG
              kubectl wait --for=condition=Ready pod/test-pod --timeout=60s || exit 1
              kubectl logs test-pod
            '''
          }
        }
      }
    }

    stage('Clean Up') {
      steps {
        withCredentials([file(credentialsId: KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG')]) {
          script {
            sh '''
              export KUBECONFIG=$KUBECONFIG
              kubectl delete pod test-pod --ignore-not-found=true
            '''
          }
        }
      }
    }

    */
  }
}
