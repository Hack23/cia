FROM ubuntu:17.04@sha256:5d41c289942008211c2964bca72800f5c9d5ea5aa4057528da617fb36463d4ab

ADD userdata.sh /root/userdata.sh
RUN chmod a+x /root/userdata.sh
RUN /root/userdata.sh
ENTRYPOINT echo "completed"
CMD ["tail", "-f", "/dev/null"]