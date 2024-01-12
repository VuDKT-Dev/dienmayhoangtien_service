pipeline {
    agent any
    tools {
        jdk 'jdk-17'
        maven 'm3'
    }

    stages {
        stage('Init') {
            steps {
                echo 'Start CI'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
