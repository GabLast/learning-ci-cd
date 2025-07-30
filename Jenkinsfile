pipeline {
    agent any

    tools {
        // 'maven-3.8.6' should correspond to a configured Maven installation name in Jenkins
        maven 'maven-3.9.11' //needs to be added in Tools in Manage Jenkins
        // jdk 'jdk21' // Optional: if you also need a specific JDK
    }

    environment {
        DOCKER_IMAGE = "spring-boot-ci-cd-learning"
        APP_VERSION = "1.0.0"
    }

    stages {
        // stage('Build and Test Maven') {
        //     steps {
        //         script {
        //             checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/GabLast/learning-ci-cd']])
        //             bat "mvn clean package"
        //         }
        //     }
        // }

        //IMPORTANT:
        //For windows: For scripts, use bat. For Linux use sh
        //For windows: use %VARIABLE_NAME%. For Linux use echo $VARIABLE_NAME
        //Powershell: Set: $var = "hola"  echo: $var

        stage('Build Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DOCKER_CREDENTIALS', passwordVariable: 'password', usernameVariable: 'username')]) {
                        checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/GabLast/learning-ci-cd']])
                        bat """
                        docker build -t $username/${DOCKER_IMAGE}:${APP_VERSION} .
                        """
                    }

                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // withCredentials([usernamePassword(credentialsId: 'testUsername', passwordVariable: 'var2', usernameVariable: 'var1')]) {
                    //     bat 'echo %var1% %var2%'
                    // }
                    withCredentials([string(credentialsId: 'DOCKER-TOKEN', variable: 'token'), usernamePassword(credentialsId: 'DOCKER_CREDENTIALS', passwordVariable: 'password', usernameVariable: 'username')]) {
                        bat 'echo %token% | docker login -u %username% --password-stdin'
                        bat """
                        docker tag %username%/${DOCKER_IMAGE}:${APP_VERSION} %username%/${DOCKER_IMAGE}:latest
                        """
                        bat """
                        docker push %username%/${DOCKER_IMAGE}:${APP_VERSION}
                        """
                    }
                }
            }
        }

//         stage('Deploy to Kubernetes') {
//             steps {
//                 script {
//                     withKubeConfig(caCertificate: 'C:\\Users\\last0\\.minikube\\ca.crt', clusterName: 'minikube', contextName: 'minikube', credentialsId: 'K8_CREDENTIALS', namespace: 'k8namespace', restrictKubeConfigAccess: false, serverUrl: 'https://127.0.0.1:64260') {
//                     }
//
//                 }
//             }
//         }
    }

    post {

        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}
