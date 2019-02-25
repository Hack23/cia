pipeline {
   agent any
  
   tools { 
        maven 'Maven' 
        jdk 'Java11' 
    }
    
   stages {

	   stage('Build') {
	      steps {
	         sh "mvn clean install -DskipTests"
	      }
	   }
	   
	   stage('Results') {
	      steps {
	          archive 'target/*.jar'
	      }
	   }
   }
}