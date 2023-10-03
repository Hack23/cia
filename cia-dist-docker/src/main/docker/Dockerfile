FROM ubuntu:22.04@sha256:9b8dec3bf938bc80fbe758d856e96fdfab5f56c39d44b0cff351e847bb1b01ea

ADD userdata.sh /root/userdata.sh
ADD dependencies/cia-dist-deb-${project.version}.deb /root/cia-dist-deb.deb
RUN chmod a+x /root/userdata.sh
RUN /root/userdata.sh
ENTRYPOINT service postgresql restart && service cia restart && echo "completed" && tail -f /dev/null
