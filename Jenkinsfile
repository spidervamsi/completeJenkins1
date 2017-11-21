pipeline {
	agent any
	tools {
        maven 'apache-maven-3.3.9' 
    }
	stages {
   	stage('test'){
			steps {
			    echo 'hey im test'
			    sh 'mvn test'
			}
		}

              }
   	post {
   	  	always {
		 
                  echo 'hey I am here'
			}
		}

}
