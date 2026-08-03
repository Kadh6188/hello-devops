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
                    credentialsId: 'docker-hub-creds',
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
    }
}