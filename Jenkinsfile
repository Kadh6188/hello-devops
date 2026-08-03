pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Source code checked out successfully'
            }
        }

        stage('Build') {
            steps {
                sh '''
                chmod +x mvnw
                ./mvnw clean package
                '''
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t kadh6188/hello-devops:1.0 .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push kadh6188/hello-devops:1.0
                    '''
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                sh 'microk8s kubectl apply -f deployment.yaml'
                sh 'microk8s kubectl apply -f service.yaml'
            }
        }
        stage('kubernetes verify') {
            steps {
                sh 'microk8s kubectl get pods'
                sh 'microk8s kubectl get services'
                sh 'microk8s kubectl get deployments'
            }
        }
    }
}