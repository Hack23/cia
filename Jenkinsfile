#!groovy
@Library('github.com/walkmod/jenkins-pipeline-shared@declarative') _

pipeline {
   agent any
  
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