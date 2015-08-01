Using alestic-32-eu-west-1/ubuntu-9.04-jaunty-base-20091011.manifest.xml (ami-605b7014)
# http://esbperformance.org/wiki/ESB_Performance_Test_Suite

3. When running on a Linux OS, tune the operating system for optimal performance as follows. Take equivalent steps for any other OS. These settings are generic settings to utilize the full port range, and to reduce the TCP fin timeout for better socket reuse, and to set a good limit on the number of file handles permitted to the process

    * Edit /etc/sysctl.conf and append

net.ipv4.ip_local_port_range = 1024 65535
net.ipv4.tcp_fin_timeout = 30
fs.file-max = 2097152
net.ipv4.tcp_tw_recycle = 1
net.ipv4.tcp_tw_reuse = 1

    * Edit /etc/security/limits.conf and append

* soft nofile 8192
* hard nofile 65535

    * Edit /etc/pam.d/common-session and append

session required pam_limits.so

SSL
http://www.akadia.com/services/ssh_test_certificate.html

# Init setup..
# cd /etc/rc2.d/
# ln -s /etc/init.d/jetty.sh S20jetty.sh
# sudo chmod +x /etc/init.d/jetty.sh
# mysqlcheck --optimize -A -u root
CREATE USER 'cia'@'localhost';
GRANT RELOAD,PROCESS ON *.* TO 'cia'@'localhost';
deb http://ftp.de.debian.org/debian sid main non-free