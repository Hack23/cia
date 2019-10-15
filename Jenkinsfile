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
	         sh "##xvfb-run --server-args="-screen 0 1280x800x24" mvn clean install -Prelease-site,all-modules -Dmaven.test.failure.ignore=true -Djavamelody.storage-directory=/tmp/javamelody-jenkins/  -DforkMode=once"
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
	         sh "##mvn sonar:sonar -Prelease-site,all-modules -Dmaven.test.failure.ignore=true -Djavamelody.storage-directory=/tmp/javamelody-jenkins/ -Dmaven.test.skip=true -Dsonar.dynamicAnalysis=reuseReports -Dsonar.host.url=http://192.168.1.15:9000/sonar/ -Dsonar.cfn.nag.reportFiles=target/cia-dist-cloudformation.yml.nagscan -Dsonar.dependencyCheck.reportPath=dependency-check-report.xml -Dsonar.dependencyCheck.htmlReportPath=dependency-check-report.html"
		      }
	   
	    }
	
	   stage ("Check Quality gate") { 
	   
	   	      steps {
	              sh "mvn com.hack23.maven:sonar-quality-gates-maven-plugin:1.0.0:inspect  -Dsonar.host.url=http://192.168.1.15:9000/sonar"
		      }
	   
	   }
	          
	   stage ("System test JDK12") {
	   
	    }
	   stage ("System test JDK13") { 	   
	   
	   }
	   stage ("System test JDK14") {
	    }
	     
	     
	     
	   stage ("Prepare cloud environment resources") {}	   
	   stage ("Publish template S3") {}
	   stage ("Publish dist S3") {}
	    
	   stage ("Setup cloud environment") {  }
	   
	   stage ("Cloud validation") {}	    
	   stage ("Web Performance Rating") { }
	   stage ("System Roles Test") { }
       stage ("Security SSL Rating") {}
	   stage ("Security Baselinescan") {  }
	
	   stage ("Tear down cloud environment") {
	       
	   }
	   
	   stage ("Completed") {}
          
   }
}
	   
   
     
     
     
 