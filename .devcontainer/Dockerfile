FROM mcr.microsoft.com/devcontainers/java:21-bookworm

# Install additional packages
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
    graphviz \
    build-essential \
    fakeroot \
    devscripts \
    debhelper \
    dh-make \
    wget \
    tzdata \
    && rm -rf /var/lib/apt/lists/*

# Set timezone to match your workflow (Europe/Stockholm)
RUN ln -fs /usr/share/zoneinfo/Europe/Stockholm /etc/localtime \
    && dpkg-reconfigure -f noninteractive tzdata

# Use the built-in MAVEN_VERSION argument from the base image
ARG MAVEN_VERSION=3.9.9
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=${PATH}:${MAVEN_HOME}/bin

# Set working directory
WORKDIR /workspace

# Cache Maven packages
RUN mkdir -p /home/vscode/.m2 \
    && chown -R vscode:vscode /home/vscode/.m2

# Define volume for Maven cache
VOLUME /home/vscode/.m2
