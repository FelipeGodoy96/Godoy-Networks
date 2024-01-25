pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                dir('Config-Server') {
                    sh 'mvn clean install'
                }
                dir('Eureka-Server') {
                    sh 'mvn clean install'
                }
				dir('Gateway-Service') {
                    sh 'mvn clean install'
                }
				dir('MS-Bookinghub') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}