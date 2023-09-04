FROM ubuntu:22.04@sha256:aabed3296a3d45cede1dc866a24476c4d7e093aa806263c27ddaadbdce3c1054

ADD userdata.sh /root/userdata.sh
ADD dependencies/cia-dist-deb-${project.version}.deb /root/cia-dist-deb.deb
RUN chmod a+x /root/userdata.sh
RUN /root/userdata.sh
ENTRYPOINT service postgresql restart && service cia restart && echo "completed" && tail -f /dev/null
