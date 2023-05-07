FROM ubuntu:22.04@sha256:dfd64a3b4296d8c9b62aa3309984f8620b98d87e47492599ee20739e8eb54fbf

ADD userdata.sh /root/userdata.sh
ADD dependencies/cia-dist-deb-${project.version}.deb /root/cia-dist-deb.deb
RUN chmod a+x /root/userdata.sh
RUN /root/userdata.sh
ENTRYPOINT service postgresql restart && service cia restart && echo "completed" && tail -f /dev/null
