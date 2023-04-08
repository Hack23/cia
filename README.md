# Citizen Intelligence Agency

Tracking politicians like bugs. Citizen Intelligence Agency is independent and non-partisan voluntary project.

# Project no longer maintained, feel free to fork!

# Goal

Visualize political activity in Sweden, present key performance indicators and metadata for the actors on national level.

Using open data from  [http://data.riksdagen.se/](http://data.riksdagen.se/) , [http://www.val.se/](http://www.val.se/) and [http://data.worldbank.org/](http://data.worldbank.org/) .


[![license](https://img.shields.io/github/license/Hack23/cia.svg)](https://raw.githubusercontent.com/Hack23/cia/master/citizen-intelligence-agency/LICENSE.txt)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FHack23%2Fcia.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FHack23%2Fcia?ref=badge_shield)
[![CLA assistant](https://cla-assistant.io/readme/badge/Hack23/cia)](https://cla-assistant.io/Hack23/cia)
[![Maven Central](https://img.shields.io/maven-central/v/com.hack23.cia/cia-dist-deb.svg)](http://mvnrepository.com/artifact/com.hack23.cia/cia-dist-deb)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/770/badge)](https://bestpractices.coreinfrastructure.org/projects/770)
[![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/Hack23/cia/badge)](https://api.securityscorecards.dev/projects/github.com/Hack23/cia)
[![CodeFactor](https://www.codefactor.io/repository/github/hack23/cia/badge)](https://www.codefactor.io/repository/github/hack23/cia)
[![Maintainability](https://api.codeclimate.com/v1/badges/14cc2db98322e8338ef1/maintainability)](https://codeclimate.com/github/Hack23/cia/maintainability)
[![codebeat badge](https://codebeat.co/badges/5a7cf18f-68cb-4535-b197-8b541bf9bb7c)](https://codebeat.co/projects/github-com-hack23-cia-master)
[![DepShield Badge](https://depshield.sonatype.org/badges/Hack23/cia/depshield.svg)](https://depshield.github.io)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/Hack23/cia.svg)](http://isitmaintained.com/project/Hack23/cia "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/Hack23/cia.svg)](http://isitmaintained.com/project/Hack23/cia "Percentage of issues still open")


# Runtime

[![JDK-17 or higher](https://img.shields.io/badge/jdk-17-green.svg)]
[![JDK-18](https://img.shields.io/badge/jdk-18-orange.svg)]
[![JDK-19](https://img.shields.io/badge/jdk-19-green.svg)]
[![JDK-20](https://img.shields.io/badge/jdk-20-orange.svg)]


#Resources

Project documentation [http://hack23.github.io/cia/](http://hack23.github.io/cia//)

Project Architecture [http://hack23.github.io/cia/architecture.html](http://hack23.github.io/cia/architecture.html)

Project Architecture(old) [https://structurizr.com/share/37264#Enterprise](https://structurizr.com/share/37264#Enterprise)

Github source code location [https://github.com/Hack23/cia](https://github.com/Hack23/cia)


# Reporting Security Issues

[Follow instructions](https://github.com/Hack23/cia/blob/master/SECURITY.md)

# Parlimentary informatics

[https://en.wikipedia.org/wiki/Parliamentary_informatics](https://en.wikipedia.org/wiki/Parliamentary_informatics) list many good examples of other projects in the domain around the world.

USA
[https://www.govtrack.us/](https://www.govtrack.us/)

Canada
[http://OpenParliament.ca](http://OpenParliament.ca)

European Union
[http://www.votewatch.eu/](http://www.votewatch.eu/)

Sweden
[http://govdata.se/api](http://govdata.se/api)

[http://riksdagsskolket.se/](http://riksdagsskolket.se/)

[http://www.demokratikollen.se](http://www.demokratikollen.se)


Denmark
[http://www.hvemstemmerhvad.dk/](http://www.hvemstemmerhvad.dk/)

Finland
[http://kansanmuisti.fi/](http://kansanmuisti.fi/)

Norway
[https://www.holderdeord.no/](https://www.holderdeord.no/)

UK
[https://www.theyworkforyou.com/](https://www.theyworkforyou.com/)

[http://www.publicwhip.org.uk/](http://www.publicwhip.org.uk/)

Germany
[http://offenesparlament.de](http://offenesparlament.de)

Italy
[http://parlamento17.openpolis.it/](http://parlamento17.openpolis.it/)

France
[https://www.nosdeputes.fr/](https://www.nosdeputes.fr/)

[https://www.projetarcadie.fr](https://projetarcadie.com)

Colombia
[http://www.congresovisible.org/](http://www.congresovisible.org/)

Jordanian
[http://www.jpm.jo/](http://www.jpm.jo/)

South Korea
[http://pokr.kr/](http://pokr.kr/)

Israel
[https://oknesset.org/](https://oknesset.org/)


# Installing Debian/Ubuntu package

 Currently only build a debian package, works with debian and ubuntu 20.4+


1. Installing database(postgres) and openjdk

```
$ sudo apt-get install openjdk-16-jdk postgresql-13 pgadmin3
```


2. Installing Postgresql on Ubuntu

```
$ sudo apt-get install postgresql-13 postgresql-contrib postgresql-13-pgaudit
```

3. Create empty database

Below description set the default username/password and database name used for development, recommend using custom credentials and update the configuration at /opt/cia/webapps/cia/WEB-INF/database.properties to define your own username/password and database name.

```
$ sudo su - postgres
$ psql
postgres=# CREATE USER eris WITH password 'discord';
postgres=# CREATE DATABASE cia_dev;
postgres=# GRANT ALL PRIVILEGES ON DATABASE cia_dev to eris;
```

4. Modify postgres setting, enable prepared transactions and extensions used pg_stat_statements, pgaudit, pgcrypto

Edit file "/etc/postgresql/13/main/postgresql.conf" set

```
max_prepared_transactions = 100
```

```
shared_preload_libraries = 'pg_stat_statements, pgaudit, pgcrypto'
pgaudit.log = ddl
pg_stat_statements.track = all
pg_stat_statements.max = 10000
```

5. Modify postgres setting
Edit file "/etc/postgresql/13/main/pg_hba.conf" add line

```
host all all ::1/128 md5
```


6. Restart postgres

```
$ service postgresql restart
```

7. Get cia debian package and


```
$ wget https://oss.sonatype.org/content/repositories/releases/com/hack23/cia/cia-dist-deb/2022.12.26/cia-dist-deb-2022.12.26.deb
```


8. Install debian package

```
$ sudo dpkg -i cia-dist-deb-2022.12.26.deb
```


9. Access the server at [https://localhost:28443/cia/](https://localhost:28443/cia/) .


# Application package diagram overview

![Package overview diagram](https://hack23.github.io/cia/apidocs/package-dependencies.svg)
[Api docs](https://hack23.github.io/cia/apidocs/index.html)

