pipeline {

    agent any

    stages {
        stage('build app1') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'aws', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                     sh '''
                        aws configure set aws_access_key_id ${USERNAME}
                        aws configure set aws_secret_access_key ${PASSWORD}
                        aws configure set default.region us-west-2
                        export CODEARTIFACT_AUTH_TOKEN=`aws codeartifact get-authorization-token --domain test --domain-owner 748392735374 --region us-west-2 --query authorizationToken --output text`
                        chmod +x ./gradlew
                        ./gradlew build
                     '''
                }
            }
        }
        stage('app1 docker build') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'aws', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                     sh """docker build --build-arg JAR_FILE=./build/libs/app1-0.0.1-SNAPSHOT.jar --tag 748392735374.dkr.ecr.us-west-2.amazonaws.com/app1:latest ."""
                }
            }
        }
        stage('app1 publish to ECR') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'aws', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh '''
                        aws configure set aws_access_key_id ${USERNAME}
                        aws configure set aws_secret_access_key ${PASSWORD}
                        aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 748392735374.dkr.ecr.us-west-2.amazonaws.com
                        docker push 748392735374.dkr.ecr.us-west-2.amazonaws.com/app1:latest
                    '''
                }
            }
        }
    }
}