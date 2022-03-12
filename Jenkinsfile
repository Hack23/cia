pipeline {
   agent any

   tools {
        maven 'Maven'
        jdk 'JDK17'
    }

   parameters {
       booleanParam(name: "RELEASE",
               description: "Build a release from current commit.",
               defaultValue: false)
   }

   stages {


	   stage('Build') {
	      steps {
	         sh "git clean -x -f"
	         sh "mvn clean install -DskipTests"
	      }
	   }

	   stage('Generate OpsDoc') {
	      steps {
	         sh "cd cia-dist-cloudformation/src/main/config/; chmod a+x *.sh; ./cfn-cloudformation-flip.sh;./generate-cloudformation-doc.sh || true"
	         archiveArtifacts "cia-dist-cloudformation/target/cloudformation-doc/**"
	      }
	   }

	   stage('QA:Unit Test') {
	     environment {
           MAVEN_OPTS = '-server -Xmx6048m -Xms6048m -Duser.timezone=CET --illegal-access=warn --add-exports java.base/sun.nio.ch=ALL-UNNAMED --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.desktop/java.awt.font=ALL-UNNAMED'
         }

	      steps {
	         sh "mvn clean install -Prelease-site,all-modules -Djavamelody.storage-directory=/tmp/javamelody-jenkins/  -DforkMode=once '-Dtest=!**.*ITest*' -DfailIfNoTests=false -Dsurefire.reportNameSuffix=UNIT"
	         archiveArtifacts "citizen-intelligence-agency/target/site/architecture/*.png"
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

	  stage('Release') {
            when {
                expression { params.RELEASE }
            }
            steps {
                sh "git reset --hard origin/master"
                sh "git pull"
                sh "git reset --hard origin/master"
                sh "mvn -B clean"
                sh "mvn -B release:prepare -Dgoals=deploy -Darguments='-Dgoals=deploy -DskipTests -Prelease-site,all-modules -Djavamelody.storage-directory=/tmp/javamelody-jenkins/  -DforkMode=once '-Dtest=!**.*ITest*' -DfailIfNoTests=false -Dsurefire.reportNameSuffix=UNIT' -DskipTests -Prelease-site,all-modules -Djavamelody.storage-directory=/tmp/javamelody-jenkins/  -DforkMode=once '-Dtest=!**.*ITest*' -DfailIfNoTests=false -Dsurefire.reportNameSuffix=UNIT"
                sh "mvn -B release:perform -Dgoals=deploy -Darguments='-Dgoals=deploy -DskipTests -Prelease-site,all-modules -Djavamelody.storage-directory=/tmp/javamelody-jenkins/  -DforkMode=once '-Dtest=!**.*ITest*' -DfailIfNoTests=false -Dsurefire.reportNameSuffix=UNIT' -DskipTests -Prelease-site,all-modules -Djavamelody.storage-directory=/tmp/javamelody-jenkins/  -DforkMode=once '-Dtest=!**.*ITest*' -DfailIfNoTests=false -Dsurefire.reportNameSuffix=UNIT"
            }
       }


	   stage('QA:System Test') {
	     environment {
           MAVEN_OPTS = '-server -Xmx6048m -Xms6048m --add-exports java.base/sun.nio.ch=ALL-UNNAMED --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.desktop/java.awt.font=ALL-UNNAMED'
         }

	      steps {
	         sh "xvfb-run --server-args='-screen 0 1920x1080x24' mvn clean install -Prelease-site,all-modules -Dmaven.test.failure.ignore=true -Djavamelody.storage-directory=/tmp/javamelody-jenkins/  -DforkMode=once"
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

	   stage ("SCA:Dependency updates") {
	      steps {
	         sh "mvn org.codehaus.mojo:versions-maven-plugin:2.8.1:dependency-updates-report -DdependencyUpdatesReportFormats=html,xml"
		      }
		}

	   stage ("SCA:Update CVE Database") {
	      tools {
    	    jdk 'Java8'
	    	}

	      steps {
	         sh "mvn org.owasp:dependency-check-maven:update-only"
		      }
	   }

	   stage ("SCA:Scan Known vulnerabilities report") {
	   	 tools {
    	    jdk 'Java8'
	    }

	      steps {
	         sh "mvn -f citizen-intelligence-agency/pom.xml org.owasp:dependency-check-maven:check -Dformat=ALL -DskipSystemScope=true -DsuppressionFile=${WORKSPACE}/parent-pom/src/config/suppressions.xml -Dscan=${WORKSPACE}/citizen-intelligence-agency/target/"
		      }
	   }

		stage ("DAST: start app") {
	     environment {
           MAVEN_OPTS = '-server -Xmx6048m -Xms6048m -Duser.timezone=CET --illegal-access=warn --add-exports java.base/sun.nio.ch=ALL-UNNAMED --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.desktop/java.awt.font=ALL-UNNAMED'
         }
	      steps {
	          sh "JETTYPID=`ss -tanp | grep 28443 | grep LISTEN | cut -d',' -f2 | cut -d'=' -f2`; kill -9 \${JETTYPID} || true"
	          sh "cd citizen-intelligence-agency; nohup mvn -e exec:java -Dexec.classpathScope='test' -Dexec.mainClass=com.hack23.cia.systemintegrationtest.CitizenIntelligenceAgencyServer > target/jettyzap.log 2>&1 &"
		  }
		}

		stage ("DAST: Scan running app") {
	      steps {
	          sh "docker system prune -a -f"
	          sh "mkdir  ${WORKSPACE}/zap-reports"
	          sh "chmod 777 ${WORKSPACE}/zap-reports"
	          sh "docker run -v ${WORKSPACE}/zap-reports:/zap/wrk/:rw owasp/zap2docker-stable zap-full-scan.py -t https://192.168.1.4:28443 -a -j -J baseline-scan-report.json -x baseline-scan-report.xml -r baseline-scan-report.html || true"
	          archiveArtifacts "**/baseline-scan-report.*"
		   }
		}

		stage ("DAST: stop app") {
	      steps {
	          sh "JETTYPID=`ss -tanp | grep 28443 | grep LISTEN | cut -d',' -f2 | cut -d'=' -f2`; kill -9 \${JETTYPID} || true"
		      }
		}

	   stage ("Build docker image") {
	   	   steps {
	              sh "mvn -f cia-dist-docker/pom.xml install"
		 }
	   }

	   stage ("Security scan docker image") {
	     environment {
           VERSION = readMavenPom().getVersion()
         }

	   	  steps {
	              sh "echo 'docker run --rm -v /var/run/docker.sock:/var/run/docker.sock -v /var/lib/jenkins/:/root/.cache/ aquasec/trivy  hack23/cia:\$VERSION --exit-code 1 --severity MEDIUM,HIGH,CRITICAL || true'"
		 }
	   }

		stage ("SAST: Scan AWS Cloudformation ") {
	      steps {
	         sh "cfn_nag --output-format=json cia-dist-cloudformation/src/main/resources/cia-dist-cloudformation.yml > cia-dist-cloudformation.yml.nagscan || true"	         	
	         archiveArtifacts "cia-dist-cloudformation.yml.nagscan"
	         sh "checkov -f cia-dist-cloudformation/src/main/resources/cia-dist-cloudformation.json -o json > cia-dist-cloudformation.checkov-report || true"
		  }
		}

	   stage ("SAST: Scan code and Check Quality gate") {
	     environment {
           MAVEN_OPTS = '-server -Xmx6048m -Xms6048m -Duser.timezone=CET --illegal-access=warn --add-exports java.base/sun.nio.ch=ALL-UNNAMED --add-exports java.base/jdk.internal.ref=ALL-UNNAMED --add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.desktop/java.awt.font=ALL-UNNAMED'
         }

	      steps {
	         sh "mvn sonar:sonar -Prelease-site,all-modules -Dmaven.test.failure.ignore=true -Djavamelody.storage-directory=/tmp/javamelody-jenkins/ -Dmaven.test.skip=true -Dsonar.dynamicAnalysis=reuseReports -Dsonar.host.url=http://192.168.1.15:9000/sonar/ -Dsonar.cfn.checkov.reportFiles=cia-dist-cloudformation.checkov-report -Dsonar.cfn.nag.reportFiles=cia-dist-cloudformation.yml.nagscan -Dsonar.dependencyCheck.xmlReportPath=citizen-intelligence-agency/target/dependency-check-report.xml -Dsonar.dependencyCheck.htmlReportPath=citizen-intelligence-agency/target/dependency-check-report.html -Dsonar.zaproxy.reportPath=zap-reports/baseline-scan-report.xml -Dsonar.zaproxy.htmlReportPath=zap-reports/baseline-scan-report.html"
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
	              sh "echo Completed"
		      }
	   }

   }
   post {
        always {
            cleanWs()
        }
    }

}

