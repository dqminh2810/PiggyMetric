//@Library('test-job-agent-2-libs') _

pipeline {
  //agent { label 'built-in' }
  agent {label "jenkins-agent"}

  environment {
    GITHUB_ORGANIZATION = "dqminh2810"
    GITHUB_REPO = "PiggyMetric"
    GITHUB_CREDENTIAL_ID = "github-api"
    WORKSPACE = "${env.WORKSPACE}"
    IMAGE_NAME = 'dqminh2810/hello-world-piggy_experience-service'
    IMAGE_TAG = "v${env.BUILD_NUMBER}"
    DOCKER_CREDENTIALS_ID = 'docker-repository-credential'
    //KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-creds'
  }

  stages {
    stage('Check ENV') {
        steps {
            sh '''
                echo "NODE_NAME is $NODE_NAME"
                echo "JOB_NAME is $JOB_NAME"
                echo "IMAGE_NAME is $IMAGE_NAME"
                echo "IMAGE_TAG is $IMAGE_TAG"
                echo "USER is $USER"
                hostname -I
                java --version
                mvn --version
                docker info
                echo "${WORKSPACE}"
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
          dockerImage = docker.build("${IMAGE_NAME}:${IMAGE_TAG}", "${WORKSPACE}/experience-service")
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

    stage('Deploy to K3S') {
        agent {
            kubernetes {
              yaml '''
                apiVersion: v1
                kind: Pod
                metadata:
                  labels:
                    app: hello-world-piggy_experience-service
                spec:
                  containers:
                  - name: maven
                    image: dqminh2810/hello-world-piggy_experience-service:v51
                    ports:
                        - containerPort: 8080
                    readinessProbe:
                      httpGet:
                        path: /actuator/health
                        port: 8080
                      initialDelaySeconds: 60
                    livenessProbe:
                      httpGet:
                        path: /actuator/health
                        port: 8080
                  imagePullSecrets:
                    - name: docker-reg-creds
                '''
            }
        }
        steps {
            sh 'echo "[K3S-JNLP] Deploy to K3S"'
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
