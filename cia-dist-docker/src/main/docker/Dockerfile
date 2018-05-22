FROM ubuntu:18.04@sha256:90f24abe180424046a5d53f6fc6f9fdb8f79b835cb2fd7d1a782e4c30dfb5dcc

ADD userdata.sh /root/userdata.sh
ADD dependencies/cia-dist-deb-${project.version}.deb /root/cia-dist-deb.deb
RUN chmod a+x /root/userdata.sh
RUN /root/userdata.sh
ENTRYPOINT service postgresql restart && service cia restart && echo "completed" && tail -f /dev/null
