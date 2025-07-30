pipeline {
    agent any
    triggers { githubPush() } // 监听 GitHub 推送事件
    stages {
        stage('Build Admin') {
            steps {
                sh 'mvn clean package -pl admin -am' // 构建 admin 模块及其依赖
            }
        }
        stage('Build Front') {
            steps {
                sh 'mvn clean package -pl front -am' // 构建 front 模块及其依赖
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -pl admin,front -am'
                junit '**/target/surefire-reports/*.xml' // 发布测试报告
            }
        }
    }
    post {
        success { githubNotify(status: 'SUCCESS', description: '构建成功!') }
        failure { githubNotify(status: 'FAILURE', description: '构建失败!') }
    }
}
