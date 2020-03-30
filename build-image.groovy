@Library ('common') _
pipeline {

    stages {
        stage ('Build Image') {
            steps{
                script{
                    sh 'git clone '+ params.gitSource + 'repo'
                    sh 'cd repo'
                    sh 'chmod 775 build_image.sh'
                    sh './build_image.sh'
                    }
                    echo 'Build image completed'
        }
        stage ('Deploy to server') {
            when {
                expression {
                    return params.toDeploy == true;
                }
            }
            steps{
                build job: params.deployJobName
                }
            }
        }
    }
}
