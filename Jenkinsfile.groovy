node {
    stage('Checkout scm') {
        checkout scm
    }
    stage('Build') {
        sh "mvn clean install"
    }
    stage('Start docker') {
        sh "docker-compose build && docker-compose up"
    }
}