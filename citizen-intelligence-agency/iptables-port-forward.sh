iptables -t nat -A OUTPUT -d localhost -p tcp --dport 80 -j REDIRECT --to-ports 8080
iptables -t nat -A OUTPUT -d 192.168.0.12 -p tcp --dport 80 -j REDIRECT --to-ports 8080
iptables -t nat -A PREROUTING -d 192.168.0.12 -p tcp --dport 80 -j REDIRECT --to-ports 8080
