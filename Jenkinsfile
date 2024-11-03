pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                // Check out the code from the specified branch and repository
                git branch: 'MedAzizChehata_5NIDS2_GP5', url: 'https://github.com/nerminetarhouni1/tpGit.git'
                echo 'CHECKING CODE --------------> Done'
            }
        }

        stage('Build Project') {
            steps {
                // Make sure Maven is installed and configured in Jenkins
                sh 'mvn clean package'
                echo 'CLEANING PACKAGES ----------> Done'
            }
        }
    }
}
