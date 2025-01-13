FROM mcr.microsoft.com/devcontainers/java:21-bookworm

# Install all packages in a single layer to reduce image size
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
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
    xvfb \
    xauth \
    google-chrome-stable \
    xdg-utils \
    libnss3 \
    libgbm1 \
    libasound2 \
    libxtst6 \
    libx11-6 \
    libxext6 \
    libxi6 \
    x11-xserver-utils \
    && sh -c 'echo "deb [signed-by=/usr/share/keyrings/postgres-archive-keyring.gpg] http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/postgresql.list' \
    && wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | gpg --dearmor -o /usr/share/keyrings/postgres-archive-keyring.gpg \
    && apt-get update \
    && apt-get -y install --no-install-recommends \
    postgresql-16 \
    postgresql-contrib-16 \
    postgresql-16-pgaudit \
    postgresql-16-pgvector \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /etc/apt/sources.list.d/pgdg.list

# Add Chrome environment variables
ENV CHROME_BIN=/usr/bin/google-chrome
ENV CHROME_PATH=/usr/lib/chromium/
ENV PUPPETEER_SKIP_CHROMIUM_DOWNLOAD=true
ENV DISPLAY=:99

# Create Chrome sandbox
RUN mkdir -p /opt/chrome/sandbox && \
    chown root:root /opt/chrome/sandbox && \
    chmod 4755 /opt/chrome/sandbox

# Configure Git
RUN git config --system core.fileMode false \
    && git config --system core.autocrlf input \
    && git config --system pull.rebase true \
    && git config --system push.default current

# Copy PostgreSQL initialization script
COPY init-postgresql.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/init-postgresql.sh \
    && ln -fs /usr/share/zoneinfo/Europe/Stockholm /etc/localtime \
    && dpkg-reconfigure -f noninteractive tzdata

# Use the built-in MAVEN_VERSION argument from the base image
ARG MAVEN_VERSION=3.9.9
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=${PATH}:${MAVEN_HOME}/bin
ENV MAVEN_OPTS="-Xmx8192m -Xms2048m -XX:+UseG1GC"
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
