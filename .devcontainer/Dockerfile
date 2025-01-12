FROM mcr.microsoft.com/devcontainers/java:21-bookworm

# Install all packages in a single layer to reduce image size
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
    git \
    curl \
    unzip \
    jq \
    shellcheck \
    vim \
    lsb-release \
    gnupg2 \
    && sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list' \
    && wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | gpg --dearmor -o /usr/share/keyrings/postgres-archive-keyring.gpg \
    && echo "deb [signed-by=/usr/share/keyrings/postgres-archive-keyring.gpg] http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/postgresql.list \
    && apt-get update \
    && apt-get -y install --no-install-recommends \
    postgresql-16 \
    postgresql-contrib-16 \
    postgresql-16-pgaudit \
    postgresql-16-pgvector \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Configure Git
RUN git config --system core.fileMode false \
    && git config --system core.autocrlf input \
    && git config --system pull.rebase true \
    && git config --system push.default current

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
