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
    }
}