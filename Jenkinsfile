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
                sh 'echo "后台服务构建开始..."'
                sh 'mvn clean package -pl admin -am -DskipTests '
                sh 'echo "后台服务构建完成..."'
            }
        }

        stage('Build Front') {
            steps {
                 sh 'echo "前端服务构建开始..."'
                sh 'mvn clean package -pl front -am -DskipTests'
                sh 'echo "前端服务构建完成..."'
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
