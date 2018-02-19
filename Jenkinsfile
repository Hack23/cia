pipeline {
   agent any
  
   tools { 
        maven 'Maven' 
        jdk 'Java8' 
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