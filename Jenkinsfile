pipeline {
    agent any
    triggers { githubPush() }
    tools {
        git 'Default'  // 使用名为Default的Git工具
    }
    stages {
        stage('Checkout') {
            steps {
                retry(3) {
                    checkout scm
                }
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
                try {
                    githubNotify(status: 'SUCCESS', description: '构建成功!')
                } catch (e) {
                    echo "GitHub通知失败: ${e}"
                }
            }
        }
        failure {
            script {
                try {
                    githubNotify(status: 'FAILURE', description: '构建失败!')
                } catch (e) {
                    echo "GitHub通知失败: ${e}"
                }
            }
        }
    }
}
