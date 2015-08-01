Requirements
------------

 To build and run the Citizen Intelligence Agency you need to install the following software.

 * Compiler & Runtime : JDK 7 (1.7)  {{{http://download.java.net/jdk7/binaries/}download}}.

 * Database : Mysql 5.1 {{{http://dev.mysql.com/downloads/mysql/5.1.html}download}}

 * Build System : Maven 2.2.1 {{{http://maven.apache.org/download.html}download}}

 * Build tool : Ant 1.8.0 {{{http://ant.apache.org/bindownload.cgi}download}}

 * Documentation generation : Graphviz {{{http://www.graphviz.org/}homepage}}	

Setup
-----
Setup the environment variables M2_HOME and add both mysql, ant and maven bin path to PATH.

Unzip the sql content file & start the mysql command line tool with this command:

 $ cd src/main/data
 
 $ unzip cia_dev.sql.zip 

 $ mysql -u root
 
To create database user in mysql use below.
-------------------------------------------

mysql> GRANT ALL PRIVILEGES ON *.* TO 'eris'@'localhost' IDENTIFIED BY 'discord' WITH GRANT OPTION;
mysql> create database cia_dev default charset utf8;
mysql> use cia_dev;
mysql> source src/main/data/cia_dev.sql;


Build the application & run test
--------------------------------
 Now everything should be ready for you to build the application,
 execute the following command to build

 $ mvn clean package jetty:run -Pdev,skip-db -Dtest=none -DfailIfNoTests=false

 or using convenient ant task
 
 $ ant run 

NOTE: Set the variable MAVEN_OPTS="-Xmx1024m -Xms512m -XX:MaxPermSize=512m" if you get java memory exception.

 This will build build a .war file and start en embedded jetty instance


Test Citizen Intelligence Agency
--------------------------------
 Now if everything has started successfully you should be able to view Citizen Intelligence Agency
 at http://localhost:8080/


Eclipse Development
-------------------

	Get Eclipse from http://www.eclipse.org/downloads/

	Install SVN client subversive
    * Subversive Update Site is a part of Galileo Update Site.
      Look at Help > Install New Software... > select Galileo - http://download.eclipse.org/release/galileo > Collaboration Tools
    * http://community.polarion.com/projects/subversive/download/eclipse/2.0/galileo-site/ - [required] Subversive SVN Connectors
    * http://community.polarion.com/projects/subversive/download/integrations/galileo-site/ - [optional] Subversive Integrations 

	Install m2eclipse , instructions at http://m2eclipse.sonatype.org/
	
	Install Spring IDE, update site: http://springide.org/updatesite/
		
	Install Jautodoc , update site http://jautodoc.sourceforge.net/update/
	
	Install EclEmma, update site http://update.eclemma.org/
	
	Install Eclipse cs, update site http://eclipse-cs.sf.net/update
	
	Install Jdepend for eclipse, update site http://andrei.gmxhome.de/eclipse/
	
	Install Pmd for eclipse, update site  http://pmd.sourceforge.net/eclipse
	
	Install Findbugs for eclipse, update site http://findbugs.cs.umd.edu/eclipse/
	
	--- Not tested below, probably be used at some point --
	
	Jboss tools http://www.jboss.org/tools/download/installation
	
	Spring tool suite http://www.springsource.com/products/sts
	-----------------------
	
Vaadin archetypes :
-------------------

mvn archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-clean -DarchetypeVersion=1.2-SNAPSHOT -DgroupId=com.hack23.cia -DartifactId=clean-project -Dversion=1.0 -Dpackaging=war

mvn archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-sample -DarchetypeVersion=1.2-SNAPSHOT -DgroupId=com.hack23.cia -DartifactId=sample-project -Dversion=1.0 -Dpackaging=war

mvn archetype:generate -DarchetypeGroupId=com.vaadin -DarchetypeArtifactId=vaadin-archetype-widget -DarchetypeVersion=1.2-SNAPSHOT -DgroupId=com.hack23.cia -DartifactId=widget-project -Dversion=1.0 -Dpackaging=jar	


Release instructions:
---------------------
mvn release:prepare -Pdev,skip-db,release 
mvn -Dgpg.passphrase="" -Darguments="-Dgpg.passphrase=" release:perform

