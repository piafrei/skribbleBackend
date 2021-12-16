node {
    stage('Checkout scm') {
        checkout scm
    }
    stage('Build') {
        sh "mvn clean install"
    }
    stage('Start docker') {
        sh "sudo docker-compose build && sudo docker-compose up"
    }
}