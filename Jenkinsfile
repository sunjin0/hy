pipeline {
    agent any
    triggers { githubPush() }
    stages {
        stage('Checkout') {
            steps {
                  checkout scm
            }
        }

        stage('Build Admin') {
            steps {
                sh 'mvn clean package -pl admin -am'
            }
        }

        stage('Build Front') {
            steps {
                sh 'mvn clean package -pl front -am'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test -pl admin,front -am'
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        success {
            script {
                    githubNotify(status: 'SUCCESS', description: '构建成功!')
            }
        }
        failure {
            script {
                    githubNotify(status: 'FAILURE', description: '构建失败!')
            }
        }
    }
}
