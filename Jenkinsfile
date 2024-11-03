pipeline {
    agent any

    stages {
	stages {stage('Checkout Code') {
		steps {
		branch: 'MedAzizChehata_5NIDS2_GP5', url: 'https://github.com/nerminetarhouni1/tpGit.git' 
		echo 'CHECKING CODE --------------> Done'
		}
	}
        stage('Build Project') {
            steps {
                // Make sure Maven is installed and configured in Jenkins
                sh 'mvn clean package'
		echo'CLEANING PACKAGES ----------> Done'
            }
        }
    }
}
