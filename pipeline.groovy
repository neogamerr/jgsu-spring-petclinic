pipeline {
    agent any

    stages {
        stage('Checkout'){
            steps {
                git branch: 'main', credentialsId: 'neogamerr', url: 'https://github.com/neogamerr/jgsu-spring-petclinic.git' 
            }
        }
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                //git 'https://github.com/neogamerr/jgsu-spring-petclinic.git'
                  //git branch: 'main', credentialsId: 'neogamerr', url: 'https://github.com/neogamerr/jgsu-spring-petclinic.git'
                  bat 'mvnw.cmd clean package'

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                always {
                    junit 'target/surefire-reports/*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
