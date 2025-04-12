pipeline {
        agent {
            docker {
                image 'maven:3.9.9-amazoncorretto-21'
                args '-u root -v /c/ProgramData/Jenkins/.jenkins/workspace/docker_prac:/workspace -w /workspace'
            }
        }

    stages {
        stage('Checkout') {
            steps {
                    git branch: 'main', 
                    url: 'https://github.com/utkarsh2004git/devops-users-honors-SDA.git'
            }
                
        }
        
        stage('Build') {
            steps {
                
                bat 'mvn clean install -DskipTests'
            }
            post {
                success {
                    
                    // junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage('Deploy artifact') {
            steps {
                withEnv(['PATH+EXTRA=/usr/sbin:/usr/bin:/sbin:/bin']) {
                    withCredentials([sshUserPrivateKey(credentialsId: "jenkins_aws_key", keyFileVariable: 'keyfile')]) {
                        bat """
                            icacls "${keyfile}" /inheritance:r || exit /b
                            icacls "${keyfile}" /grant:r "NT AUTHORITY\\SYSTEM:R" || exit /b
                            scp -o StrictHostKeyChecking=no -i "${keyfile}" target/devops-0.0.1-SNAPSHOT.jar ec2-user@ec2-16-171-19-67.eu-north-1.compute.amazonaws.com:/home/ec2-user
                        """
                    }
                }
            }
        }

    }
}