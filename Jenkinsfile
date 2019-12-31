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
	   	   
	   stage('QA:Test') {
	     environment {
           MAVEN_OPTS = '-server -Xmx6048m -Xms6048m --add-exports java.base/sun.nio.ch=ALL-UNNAMED --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.desktop/java.awt.font=ALL-UNNAMED'
         }
	   
	      steps {
	         sh "xvfb-run --server-args='-screen 0 1280x800x24' mvn clean install -Prelease-site,all-modules -Dmaven.test.failure.ignore=true -Djavamelody.storage-directory=/tmp/javamelody-jenkins/  -DforkMode=once"
	      }
	        post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    
                    jacoco( 
				      execPattern: '**/target/jacoco.exec',
				      classPattern: '**/target/classes',
				      sourcePattern: '**/src/main/java',
				      exclusionPattern: '**/src/test*'
				   )
                    
                }
            }
	   }
	   		   	   
	   stage ("SAST: Scan code and submit reports") { 
	      steps {
	         sh "mvn sonar:sonar -Prelease-site,all-modules -Dmaven.test.failure.ignore=true -Djavamelody.storage-directory=/tmp/javamelody-jenkins/ -Dmaven.test.skip=true -Dsonar.dynamicAnalysis=reuseReports -Dsonar.host.url=http://192.168.1.15:9000/sonar/ -Dsonar.cfn.nag.reportFiles=target/cia-dist-cloudformation.yml.nagscan -Dsonar.dependencyCheck.reportPath=citizen-intelligence-agency/target/dependency-check-report.xml -Dsonar.dependencyCheck.htmlReportPath=citizen-intelligence-agency/target/dependency-check-report.html -Dsonar.zaproxy.reportPath=${WORKSPACE}/baseline-scan-report.xml"
		      }	   
	    }
	
	   stage ("Check Quality gate") { 
	   
	   	      steps {
	              sh "mvn com.hack23.maven:sonar-quality-gates-maven-plugin:1.0.0:inspect  -Dsonar.host.url=http://192.168.1.15:9000/sonar"
		      }
	   
	   }	     
	          
	   stage ("System test JDK12") {
	   	   	 tools { 
    	    jdk 'JDK12' 
	    }
	   
	   	   	      steps {
	              sh "echo placeholder"
		      }
	    }
	    
	   stage ("System test JDK13") { 	
	   	   	 tools { 
    	    jdk 'JDK13' 
	    }

	   	   	   	      steps {
	              sh "echo placeholder"
		      }   
	   
	   }
	   stage ("System test JDK14") {
	   	   	 tools { 
    	    jdk 'JDK14' 
	    }
	   	   	   	      steps {
	              sh "echo placeholder"
		      }	   
	   }	     
	     
	     
	   stage ("Prepare cloud environment resources") {	   	   	      steps {
	              sh "echo placeholder"
		      }
	   }	   
	   stage ("Publish template S3") {	   	   	      steps {
	              sh "echo placeholder"
		      }
	   }
	   stage ("Publish dist S3") {	   	   	      steps {
	              sh "echo placeholder"
		      }
	   }
	    
	   stage ("Setup cloud environment") { 	   	   	      steps {
	              sh "echo placeholder"
		      }
	    }
	   
	   stage ("Cloud validation") {	   	   	      steps {
	              sh "echo placeholder"
		      }
	   }	    
	   stage ("Web Performance Rating") {	   	   	      steps {
	              sh "echo placeholder"
		      }
	    }
	   stage ("System Roles Test") {	   	   	      steps {
	              sh "echo placeholder"
		      }
	    }
       stage ("Security SSL Rating") {	   	   	      steps {
	              sh "echo placeholder"
		      }
       }
	   stage ("Security Baselinescan") {	   	   	      steps {
	              sh "echo placeholder"
		      }
	     }
	
	   stage ("Tear down cloud environment") {
	       	   	   	      steps {
	              sh "echo placeholder"
		      }
	       
	   }
	   
	   stage ("Completed") {	       	   	   	      steps {
	              sh "echo placeholder"
		      }
	   }
          
   }
}
	   
   
     
     
     
 