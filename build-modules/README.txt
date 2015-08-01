Build all 
---------
mvn clean install -Pdownload-repositories

Build site
----------
clean install site -Psite-run,release-site,download-repositories

Release module
--------------
1. mvn release:prepare
2. mvn release:perform

NOTE: 

Maven profile "download-repositories" contains all needed dependencies
locations. Only required if get unknown dependencies. 

Maven profile "release-site" contains configuration to generate site documentation.
