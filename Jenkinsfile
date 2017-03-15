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
         sh "/opt/tools/maven/bin/mvn pmd:check"
      }
   }
   stage('Build') {
      steps {
         sh "/opt/tools/maven/bin/mvn package"
      }
   }
   stage('Results') {
      steps {
          archive 'target/*.jar'
      }
   }
   }
}