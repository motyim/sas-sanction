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
                    sh 'rm -rf /sas/deployment/sas-sanction.jar'
                    sh 'cp -rp /var/lib/jenkins/workspace/demo/target/sas-sanction.jar /sas/deployment'
                    sh 'service sas-sanction-demo start'
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