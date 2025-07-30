pipeline {
    agent any
    triggers { githubPush() } // 监听 GitHub 推送事件
    stages {
        stage('Build') {
            steps { sh 'mvn clean package' } // 构建 JAR
        }
        stage('Test') {
            steps { 
                sh 'mvn test' 
                junit '**/target/surefire-reports/*.xml' // 发布测试报告
            }
        }
    }
    post {
        success { githubNotify(status: 'SUCCESS', description: '构建成功!') }
        failure { githubNotify(status: 'FAILURE', description: '构建失败!') }
    }
}
