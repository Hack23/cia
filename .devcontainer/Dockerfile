FROM mcr.microsoft.com/devcontainers/java:21-bookworm

# Install additional packages
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install \
    graphviz \
    build-essential \
    fakeroot \
    devscripts \
    debhelper \
    dh-make \
    wget \
    tzdata \
    && rm -rf /var/lib/apt/lists/*

# Install PostgreSQL 15
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install \
    postgresql-15 \
    postgresql-contrib-15 \
    postgresql-15-pgaudit \
    && rm -rf /var/lib/apt/lists/*

# Install additional development tools
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install \
        git \
        curl \
        unzip \
        jq \
        shellcheck \
        vim \
    && rm -rf /var/lib/apt/lists/*

# Configure Git
RUN git config --system core.fileMode false \
    && git config --system core.autocrlf input \
    && git config --system pull.rebase true \
    && git config --system push.default current

# Install Checkstyle
RUN wget https://github.com/checkstyle/checkstyle/releases/download/checkstyle-10.12.7/checkstyle-10.12.7-all.jar \
    -O /usr/local/lib/checkstyle.jar \
    && wget https://raw.githubusercontent.com/checkstyle/checkstyle/master/src/main/resources/google_checks.xml \
    -O /google_checks.xml

# Copy PostgreSQL initialization script
COPY init-postgresql.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/init-postgresql.sh

# Set timezone to match your workflow (Europe/Stockholm)
RUN ln -fs /usr/share/zoneinfo/Europe/Stockholm /etc/localtime \
    && dpkg-reconfigure -f noninteractive tzdata

# Use the built-in MAVEN_VERSION argument from the base image
ARG MAVEN_VERSION=3.9.9
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=${PATH}:${MAVEN_HOME}/bin
ENV MAVEN_OPTS="-Xmx2048m -Xms1024m -XX:+UseG1GC"
ENV JAVA_TOOL_OPTIONS="\
    --add-exports=java.base/sun.nio.ch=ALL-UNNAMED \
    --add-exports=java.base/jdk.internal.ref=ALL-UNNAMED \
    --add-opens=java.base/java.lang=ALL-UNNAMED \
    --add-opens=java.base/java.lang.reflect=ALL-UNNAMED \
    --add-opens=java.base/java.util=ALL-UNNAMED"

# Set working directory
WORKDIR /workspace

# Cache Maven packages
RUN mkdir -p /home/vscode/.m2 \
    && chown -R vscode:vscode /home/vscode/.m2

# Define volume for Maven cache
VOLUME /home/vscode/.m2

# Copy settings.xml
COPY --chown=vscode:vscode settings.xml /home/vscode/.m2/settings.xml
