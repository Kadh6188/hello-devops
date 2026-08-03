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
                sh 'docker build -t hello-devops:1.0 .'
            }
        }

    }
}