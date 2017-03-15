#!groovy
@Library('github.com/walkmod/jenkins-pipeline-shared@declarative') _

pipeline {
   agent any
  
   tools { 
        maven 'Maven339' 
        jdk 'Java8' 
    }
    
   stages {

   stage ('Fixing Release'){
      steps {
        walkmodApply()
      }        
   }
   stage ('Check conventions'){
      steps {
         sh "mvn pmd:check"
      }
   }
   stage('Build') {
      steps {
         sh "mvn package"
      }
   }
   stage('Results') {
      steps {
          archive 'target/*.jar'
      }
   }
   }
}