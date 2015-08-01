#!/bin/bash
set -e -x
export DEBIAN_FRONTEND=noninteractive
#
echo "Updating APT, upgrading packages"
sudo apt-get update 
sudo apt-get upgrade -y 
#
echo "Installing System tools"
sudo apt-get install apt-show-versions unzip -y
#
echo "Installing Web frontend tools"
sudo apt-get install apache2 awstats libapache2-mod-geoip libgeoip-dev -y
sudo wget http://geolite.maxmind.com/download/geoip/database/GeoLiteCountry/GeoIP.dat.gz
sudo gunzip GeoIP.dat.gz
sudo mkdir -p /var/lib/GeoIP
sudo cp GeoIP.dat /var/lib/GeoIP/GeoIP.dat
sudo wget http://geolite.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz
sudo gunzip GeoLiteCity.dat.gz
sudo cp GeoLiteCity.dat /var/lib/GeoIP/GeoLiteCity.dat
sudo a2enmod proxy_http
sudo a2enmod ssl
sudo a2enmod rewrite

#
echo "Installing Database tools"
sudo apt-get install mysql-server-5.1 mysqltuner postgresql -y  
#
echo "Installing Java tools" 
sudo apt-get install openjdk-6-jdk -y 
#
echo "Installing Application Server"
wget http://download.eclipse.org/jetty/7.2.0.v20101020/dist/jetty-distribution-7.2.0.v20101020.zip
#
echo "Installing Monitoring Tools"
#
echo "Prepare Configuration"
sudo adduser --system --shell /bin/sh --gecos 'Citizen Intelligence Agency' --group --disabled-password --home /home/cia cia

#
echo "Install Citizen Intelligence Agency"
#
echo "Start all services"
