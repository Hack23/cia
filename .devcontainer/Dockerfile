FROM mcr.microsoft.com/devcontainers/java:21-bullseye

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

# Install specific Maven version (3.9.9 to match your workflow)
ARG MAVEN_VERSION=3.9.9
RUN curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    | tar xzvf - -C /usr/local/ \
    && ln -s /usr/local/apache-maven-${MAVEN_VERSION} /usr/local/maven \
    && ln -s /usr/local/maven/bin/mvn /usr/local/bin/mvn

# Set Maven environment variables
ENV MAVEN_HOME=/usr/local/maven
ENV PATH=${PATH}:${MAVEN_HOME}/bin

# Set working directory
WORKDIR /workspace

# Cache Maven packages
VOLUME /root/.m2