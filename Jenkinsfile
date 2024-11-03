pipeline {
    agent any

    stages {
        stage('Build Project') {
            steps {
                // Make sure Maven is installed and configured in Jenkins
                sh 'mvn clean package'
		echo'CLEANING PACKAGES ----------> Done'
            }
        }
    }
}
