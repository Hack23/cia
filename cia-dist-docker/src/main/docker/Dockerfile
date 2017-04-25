FROM ubuntu:17.04

ADD userdata.sh /root/userdata.sh
RUN chmod a+x /root/userdata.sh
RUN /root/userdata.sh
ENTRYPOINT echo "completed"
CMD ["tail", "-f", "/dev/null"]