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
                }
            }
            post{
                success{
                    sh "service ${JOB_NAME} stop"
                    sh "rm -rf /sas/deployment/${JOB_NAME}.jar"
                    sh "cp -rp /var/lib/jenkins/workspace/${JOB_NAME}/target/${JOB_NAME}.jar /sas/deployment"
                    sh "service ${JOB_NAME} start"
                    notify("Finished Stage Successfully")
                }
            }
        }
    }
}

def notify(status){
    emailext (
      to: "mohamed.elmotyim@sas.com",
      subject: "Build Finished",
      body: """${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
        Check console output at : ${env.BUILD_URL} ${env.JOB_NAME} [${env.BUILD_NUMBER}]""",
    )
}