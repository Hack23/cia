FROM ubuntu:17.04@sha256:213e05583a7cb8756a3f998e6dd65204ddb6b4c128e2175dcdf174cdf1877459

ADD userdata.sh /root/userdata.sh
RUN chmod a+x /root/userdata.sh
RUN /root/userdata.sh
ENTRYPOINT echo "completed"
CMD ["tail", "-f", "/dev/null"]