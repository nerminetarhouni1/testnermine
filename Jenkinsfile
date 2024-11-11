pipeline {                                                                                                                                                                                                             
    agent any                                                                                                                                                                                                                                                                                                                                                                                                                             
    stages {                                                                                                                                                                                                             
        stage('Checkout Code') {                                                                                                                                                                                              
            steps {                                                                                                                                                                                                               
                git branch: 'MedAzizChehata_5NIDS2_GP5', url: 'https://github.com/nerminetarhouni1/tpGit.git'                                                                                                                      
                echo 'CHECKING CODE ---------------------------------------------------------------------------------------------> Done'                                                                                       
            }                                                                                                                                                                                                             
        }                                                                                                                                                                                                                                                                                                                                                                                                                                     
        stage('Build Project') {                                                                                                                                                                                              
            steps {                                                                                                                                                                                                               
                sh 'mvn clean package'                                                                                                                                                                                             
                echo 'CLEANING PACKAGES -----------------------------------------------------------------------------------------> Done'                                                                                       
            }                                                                                                                                                                                                             
        }                                                                                                                                                                                                                  
        stage('Docker Down') {                                                                                                                                                                                                 
            steps {                                                                                                                                                                                                               
                sh 'docker-compose down'                                                                                                                                                                                          
                echo 'Docker Down ------------------------------------------------------------------------------------------> Done'                                                                                            
            }                                                                                                                                                                                                             
        }                                                                                                                                                                                                                  
        stage('Building Docker') {                                                                                                                                                                                             
            steps {                                                                                                                                                                                                               
                sh 'docker-compose up --build -d'                                                                                                                                                                                  
                echo 'Building Docker ------------------------------------------------------------------------------------------> Done'                                                                                        
            }                                                                                                                                                                                                             
        }                                                                                                                                                                                                             
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    // Using Jenkins credentials for Docker Hub login
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        // Login to Docker Hub
                        sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
                        
                        // Tag the Docker image
                        sh 'docker tag med_aziz_chehata_5nids2_grp5_tp-foyer:latest chehatamedaziz/med_aziz_chehata_5nids2_grp5_tp-foyer:latest'
                        
                        // Push the image to Docker Hub
                        sh 'docker push chehatamedaziz/med_aziz_chehata_5nids2_grp5_tp-foyer:latest'
                    }
                }
                echo 'Pushing Docker Image to Docker Hub ---------------------------------------------------------------------------------> Done'
            }
        }
    }                                                                                                                                                                                                             
}

