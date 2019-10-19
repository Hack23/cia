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
	   }
	   	   
	   stage ("SCA:Dependency updates") {  
	      steps {
	         sh "mvn org.codehaus.mojo:versions-maven-plugin:2.7:dependency-updates-report -DdependencyUpdatesReportFormats=html,xml"
		      }
		}
	
	   stage ("SCA:Update CVE Database") { 
	      tools { 
    	    jdk 'Java8' 
	    	}
	    	   
	      steps {
	         sh "mvn org.owasp:dependency-check-maven:5.2.1:update-only"
		      }
	   }
	
	   stage ("SCA:Scan Known vulnerabilities report") { 
	   	 tools { 
    	    jdk 'Java8' 
	    }
	    	   
	      steps {
	         sh "mvn -f citizen-intelligence-agency/pom.xml org.owasp:dependency-check-maven:5.2.1:check -Dformat=ALL -DskipSystemScope=true -DsuppressionFile=${WORKSPACE}/parent-pom/src/config/suppressions.xml -Dscan=${WORKSPACE}/citizen-intelligence-agency/target/"
		      }
	   }

		stage ("DAST: start app") {  
	      steps {
	          sh "JETTYPID=`ss -tanp | grep 28443 | grep LISTEN | cut -d',' -f2 | cut -d'=' -f2`; kill -9 \${JETTYPID} || true"	      
	          sh "cd citizen-intelligence-agency; nohup mvn -e exec:java -Dexec.classpathScope='test' -Dexec.mainClass=com.hack23.cia.systemintegrationtest.CitizenIntelligenceAgencyServer > target/jettyzap.log 2>&1 &"
		  }
		}
	
		stage ("DAST: Scan running app") {  
	      steps {
	          sh "docker system prune -a -f"
	          sh "docker run -v ${WORKSPACE}:/zap/wrk/:rw owasp/zap2docker-weekly zap-baseline.py  -t https://192.168.1.12:28443  -J ${WORKSPACE}/baseline-scan-report.json report_json -x ${WORKSPACE}/baseline-scan-report.xml -r ${WORKSPACE}/baseline-scan-report.html || true"
		      }
		}

		stage ("DAST: stop app") {  
	      steps {
	          sh "JETTYPID=`ss -tanp | grep 28443 | grep LISTEN | cut -d',' -f2 | cut -d'=' -f2`; kill -9 \${JETTYPID} || true"
		      }
		}
		
	   stage ("Build docker image") {
	   	   steps {
	              sh "echo placeholder"
		 }
	   }
	   
	   stage ("Security scan docker image") {
	   	   steps {
	              sh "echo placeholder"
		 }
	   }
		
		stage ("SAST: Scan AWS Cloud report") {  
	      steps {
	         sh "cfn_nag --output-format=json cia-dist-cloudformation/src/main/resources/cia-dist-cloudformation.yml > target/cia-dist-cloudformation.yml.nagscan || true"
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
	   
   
     
     
     
 