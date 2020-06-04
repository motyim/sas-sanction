pipeline {
    agent any


    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'maven') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage') {

            steps {
                withMaven(maven : 'maven') {
                    sh 'mvn test'
                }
            }
        }


        stage ('Deployment Stage') {
            steps {
                withMaven(maven : 'maven') {
                    sh 'mvn package'
                    sh 'mvn spring-boot:run'
                }
            }
            post{
                success{
                    notify("Finished Stage Successfully")
                }
            }
        }
    }
}

def notify(status){
    emailext (
      to: "mohamed.elmotyim@sas.com",
      subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
      body: """${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
        Check console output at : ${env.BUILD_URL} ${env.JOB_NAME} [${env.BUILD_NUMBER}]""",
    )
}