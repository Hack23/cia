pipeline {
   agent any
  
   tools { 
        maven 'Maven' 
        jdk 'Java11' 
    }
    
   environment {
        MAVEN_OPTS = '-server -Xmx6048m -Xms6048m --add-exports java.base/sun.nio.ch=ALL-UNNAMED --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.desktop/java.awt.font=ALL-UNNAMED'
   }
    
   stages {

	   stage('Build') {
	      steps {
	         sh "mvn clean install -DskipTests"
	      }
	   }
	   
	   stage('QA:Test') {
	      steps {
	         sh "echo placeholder"
	      }
	   }
	   	   
	   stage ("SCA:Dependency updates") {  
	      steps {
	         sh "mvn org.codehaus.mojo:versions-maven-plugin:2.7:dependency-updates-report -DdependencyUpdatesReportFormats=html,xml"
		      }
		}
	
	   stage ("SCA:Known vulnerabilities") {  
	   
	      steps {
	         sh "mvn org.owasp:dependency-check-maven:5.2.2:check -Dformat=ALL -Dsuppression=$PWD/parent-pom/src/config/suppressions.xml -Dscan=$PWD/citizen-intelligence-agency/target/"
		      }
	   }
	
	
		stage ("SAST:AWS Cloud setup") {  
	      steps {
	         sh "cfn_nag --output-format=json cia-dist-cloudformation/src/main/resources/cia-dist-cloudformation.yml > cia-dist-cloudformation/target/cia-dist-cloudformation.yml.nagscan | true"
		      }
		}
		   	   
	   stage ("publish QA result to sonarqube") { 
	      steps {
	         sh "echo placeholder"
		      }
	   
	    }
	
	   stage ("Check Quality gate") { 
	   
	   	      steps {
	              sh "mvn com.hack23.maven:sonar-quality-gates-maven-plugin:1.0.0:inspect  -Dsonar.host.url=http://192.168.1.15:9000/sonar"
		      }
	   
	   }
	          
	   stage ("System test JDK12") {
	   	   	      steps {
	              sh "echo placeholder"
		      }
	    }
	   stage ("System test JDK13") { 	
	   	   	   	      steps {
	              sh "echo placeholder"
		      }   
	   
	   }
	   stage ("System test JDK14") {
	   
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
	   
   
     
     
     
 