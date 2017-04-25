#!/bin/bash -xe
exec > >(tee /var/log/user-data.log) 2>&1 
export DEBIAN_FRONTEND=noninteractive

#
# Update apt
#
apt-get update

#
# Time and locale settings
#
apt-get -y install locales tzdata
rm /etc/localtime
ln -s /usr/share/zoneinfo/Europe/Stockholm /etc/localtime 
dpkg-reconfigure -f noninteractive tzdata 
echo 'LANG=\"en_US.UTF-8\"'> /etc/default/locale
locale-gen en_US.UTF-8 en_GB.UTF-8 sv_SE.UTF-8 
dpkg-reconfigure --frontend=noninteractive locales 

#
# INSTALL ORACLE JDK
#
apt-get -y install openjdk-8-jdk-headless software-properties-common
add-apt-repository ppa:webupd8team/java
apt-get update
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
apt-get -y install oracle-java8-installer
apt-get -y install oracle-java8-unlimited-jce-policy
apt-get -y install oracle-java8-set-default

echo completed