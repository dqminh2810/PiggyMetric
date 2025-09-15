//@Library('test-job-agent-2-libs') _

pipeline {
  //agent { label 'built-in' }
  agent {label "jenkins-agent"}

  environment {
    GITHUB_ORGANIZATION = "dqminh2810"
    GITHUB_REPO = "PiggyMetric"
    GITHUB_CREDENTIAL_ID = "github-api"
    WORKSPACE = "${env.WORKSPACE}"
    IMAGE_NAME_MS_CONFIG = 'dqminh2810/hello-world-piggy_config'
    IMAGE_NAME_MS_EXPERIENCE = 'dqminh2810/hello-world-piggy_experience-service'
    IMAGE_TAG = "${env.BUILD_NUMBER}-${env.GIT_COMMIT?.take(7) ?: 'dev'}"
    DOCKER_CREDENTIALS_ID = 'docker-repository-credential'
    //KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-creds'
  }

  stages {
    stage('Check ENV') {
        steps {
            sh '''
                echo "NODE_NAME is $NODE_NAME"
                echo "JOB_NAME is $JOB_NAME"
                echo "IMAGE_NAME_MS_CONFIG is IMAGE_NAME_MS_CONFIG"
                echo "IMAGE_NAME_MS_EXPERIENCE is IMAGE_NAME_MS_EXPERIENCE"
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
          dockerImageMsConfig = docker.build("${IMAGE_NAME_MS_CONFIG}:${IMAGE_TAG}", "${WORKSPACE}/config")
//           dockerImageMsExperience = docker.build("${IMAGE_NAME_MS_EXPERIENCE}:${IMAGE_TAG}", "${WORKSPACE}/experience-service")
        }
      }
    }

    stage('Push Docker Image') {
      steps {
        script {
          docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
            dockerImageMsConfig.push()
//             dockerImageMsExperience.push()
          }
        }
      }
    }

    stage('Deploy to K3S') {
        agent {
            kubernetes {
                label 'k8s-deployer'
                defaultContainer 'kubectl'
                yaml """
                  apiVersion: v1
                  kind: Pod
                  spec:
                    serviceAccountName: jenkins-sa
                    containers:
                      - name: kubectl
                        image: bitnami/kubectl:1.29
                        command: ['sh','-c','sleep 3600']
                        tty: true
                """
            }
        }
        steps {
                            sh 'kubectl version'
                            sh 'kubectl get ns'
        //                 sh """
        //                     set -euxo pipefail
        //
        //                     # Render manifest with the current image
        //                     sed "s|IMAGE_PLACEHOLDER|${IMAGE_NAME_MS_CONFIG}:${IMAGE_TAG}|g" test-pod.yaml > pod.yaml
        //
        //                     # Apply + wait for rollout
        //                     kubectl apply -f pod.yaml
        //                 """
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
