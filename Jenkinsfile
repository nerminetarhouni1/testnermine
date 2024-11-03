pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'MedAzizChehata_5NIDS2_GP5', url: 'https://github.com/nerminetarhouni1/tpGit.git'
                echo 'CHECKING CODE --------------> Done'
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean package'
                echo 'CLEANING PACKAGES ----------> Done'
            }
        }
        stage('Building Docker') {
            steps {
                sh 'mvn clean package'
                echo 'CLEANING PACKAGES ----------> Done'
            }
        }              
    }
}
