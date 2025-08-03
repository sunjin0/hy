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
                sh 'mvn clean package -pl admin -am'
                sh 'echo "后台服务构建完成..."'
            }
        }


        stage('Build Docker Images') {
            steps {
                sh 'echo "构建 Docker 镜像..."'
                // 为 admin 服务构建 Docker 镜像
                sh 'docker build -t admin-service:latest admin/'
                sh 'echo "Docker 镜像构建完成..."'
            }
        }

        stage('Deploy to Docker') {
            steps {
                sh 'echo "部署到 Docker 容器..."'
                // 停止并删除现有容器（如果存在）
                sh 'docker stop admin-container || true'
                sh 'docker rm admin-container || true'
                // 运行新的容器
                sh 'docker run -d --name admin-container -p 8080:8080 admin-service:latest'
                sh 'echo "部署完成..."'
            }
        }
    }

    post {
   success {
          script {
              step([$class: 'GitHubCommitStatusSetter',
                    reposSource: [$class: 'ManuallyEnteredRepositorySource', url: 'your-repo-url'],
                    contextSource: [$class: 'ManuallyEnteredCommitContextSource', context: 'jenkins-ci'],
                    statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildResult', message: 'SUCCESS', state: 'SUCCESS']]]])
          }
      }
      failure {
          script {
              step([$class: 'GitHubCommitStatusSetter',
                    reposSource: [$class: 'ManuallyEnteredRepositorySource', url: 'your-repo-url'],
                    contextSource: [$class: 'ManuallyEnteredCommitContextSource', context: 'jenkins-ci'],
                    statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildResult', message: 'FAILURE', state: 'FAILURE']]]])
          }
      }
    }
}
