FROM ubuntu:22.04@sha256:6120be6a2b7ce665d0cbddc3ce6eae60fe94637c6a66985312d1f02f63cc0bcd

ADD userdata.sh /root/userdata.sh
ADD dependencies/cia-dist-deb-${project.version}.deb /root/cia-dist-deb.deb
RUN chmod a+x /root/userdata.sh
RUN /root/userdata.sh
ENTRYPOINT service postgresql restart && service cia restart && echo "completed" && tail -f /dev/null
