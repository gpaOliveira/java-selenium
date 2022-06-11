FROM ubuntu:18.04

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

################################################################################################################
# Install JDK
# https://github.com/docker-library/openjdk/blob/master/8-jdk/Dockerfile
# Selenum is based on Xenial, so it is a bit different
################################################################################################################

# add a simple script that can auto-detect the appropriate JAVA_HOME value
# based on whether the JDK or only the JRE is installed
RUN { \
    echo '#!/bin/sh'; \
    echo 'set -e'; \
    echo; \
    echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
  } > /usr/local/bin/docker-java-home \
  && chmod +x /usr/local/bin/docker-java-home

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

ENV JAVA_VERSION 8u312
ENV JAVA_DEBIAN_VERSION 8u312-b07-0ubuntu1~18.04

RUN set -x \
  && apt-get update \
  && apt-get install -y \
    curl \
    wget \
    make \
    bzip2 \
    unzip \
    xz-utils \
    fonts-liberation \
    xdg-utils \
    libxkbcommon0 \
    libgbm1 \
    libgtk-3-bin \
    openjdk-8-jdk="$JAVA_DEBIAN_VERSION" \
  && rm -rf /var/lib/apt/lists/* \
  && [ "$JAVA_HOME" = "$(docker-java-home)" ]

# see CA_CERTIFICATES_JAVA_VERSION notes above
RUN /var/lib/dpkg/info/ca-certificates-java.postinst configure

################################################################################################################
# Install Maven
# https://github.com/carlossg/docker-maven
# https://medium.com/@ahamedabdulrahman/dockerize-selenium-java-project-and-run-selenium-scripts-within-docker-container-c2603d1bac3f
################################################################################################################

ARG MAVEN_VERSION=3.8.5
ARG USER_HOME_DIR="/root"
ARG SHA=89ab8ece99292476447ef6a6800d9842bbb60787b9b8a45c103aa61d2f205a971d8c3ddfb8b03e514455b4173602bd015e82958c0b3ddc1728a57126f773c743
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

################################################################################################################
# Install chrome latest - see below reference to install a particular version
# https://medium.com/@ahamedabdulrahman/dockerize-selenium-java-project-and-run-selenium-scripts-within-docker-container-c2603d1bac3f
################################################################################################################
RUN curl -s -o /chrome.deb https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN dpkg -i /chrome.deb
RUN rm /chrome.deb

################################################################################################################
# Put our stuff
################################################################################################################
COPY . /
WORKDIR /
