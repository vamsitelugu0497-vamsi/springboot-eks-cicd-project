pipeline {

    agent any

    environment {

        IMAGE_NAME = "vamsi0497/springboot"

        AWS_REGION = "us-east-1"

        EKS_CLUSTER = "devops-eks"

        SONAR_HOST = "http://172.31.14.10:9000"
    }

    stages {

        stage('Checkout') {

            steps {

                git branch: 'main',
                url: 'https://github.com/vamsitelugu0497-vamsi/springboot-eks-cicd-project.git'
            }
        }

        stage('Build') {

            steps {

                sh '''
                mvn clean package
                '''
            }
        }

        stage('SonarQube Scan') {

            steps {

                withCredentials([
                    string(
                        credentialsId: 'sonar',
                        variable: 'SONAR_TOKEN'
                    )
                ]) {

                    sh """
                    mvn sonar:sonar \
                    -Dsonar.projectKey=springboot \
                    -Dsonar.host.url=${SONAR_HOST} \
                    -Dsonar.token=${SONAR_TOKEN}
                    """
                }
            }
        }

        stage('Docker Build') {

            steps {

                sh """
                docker build \
                -t ${IMAGE_NAME}:${BUILD_NUMBER} .
                """
            }
        }

        stage('Docker Push') {

            steps {

                script {

                    docker.withRegistry(
                        'https://index.docker.io/v1/',
                        'dockerhub'
                    ) {

                        sh """
                        docker push ${IMAGE_NAME}:${BUILD_NUMBER}
                        """
                    }
                }
            }
        }

        stage('Configure EKS') {

            steps {

                sh """
                aws eks update-kubeconfig \
                --region ${AWS_REGION} \
                --name ${EKS_CLUSTER}
                """
            }
        }

        stage('Update Manifest') {

            steps {

                sh """
                sed -i 's/replaceImageTag/${BUILD_NUMBER}/g' \
                kubernetes/deployment.yml

                cat kubernetes/deployment.yml
                """
            }
        }

        stage('Deploy To EKS') {

            steps {

                sh '''
                kubectl apply -f kubernetes/namespace.yml

                kubectl apply -f kubernetes/configmap.yml

                kubectl apply -f kubernetes/deployment.yml

                kubectl apply -f kubernetes/service.yml

                kubectl apply -f kubernetes/hpa.yml
                '''
            }
        }

        stage('Verify Deployment') {

            steps {

                sh '''
                kubectl rollout status deployment/spring-boot-app -n devops

                kubectl get deployments -n devops

                kubectl get pods -n devops

                kubectl get svc -n devops
                '''
            }
        }
    }

    post {

        success {

            echo 'Application deployed successfully to EKS'
        }

        failure {

            echo 'Pipeline failed'
        }
    }
}
