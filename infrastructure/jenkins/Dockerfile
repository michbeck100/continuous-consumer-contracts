FROM jenkins/jenkins:2.190.1-alpine

# Install required plugins
RUN /usr/local/bin/install-plugins.sh git:3.12.1 blueocean:1.19.0 job-dsl:1.76 workflow-aggregator:2.6

USER root

# From https://github.com/pact-foundation/pact-ruby-standalone/wiki/Using-the-pact-ruby-standalone-with-Alpine-Linux-Docker
RUN apk add --no-cache --virtual build-dependencies build-base
RUN apk --no-cache add ca-certificates wget bash \
  && wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub \
  && wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.29-r0/glibc-2.29-r0.apk \
  && apk add glibc-2.29-r0.apk

RUN curl -LO https://github.com/pact-foundation/pact-ruby-standalone/releases/download/v1.70.2/pact-1.70.2-linux-x86_64.tar.gz \
    && tar xzf pact-1.70.2-linux-x86_64.tar.gz \
    && rm -f pact-1.70.2-linux-x86_64.tar.gz

RUN ln -s /pact/bin/pact /usr/local/bin \
    && ln -s /pact/bin/pact-broker /usr/local/bin \
    && ln -s /pact/bin/pact-message /usr/local/bin \
    && ln -s /pact/bin/pact-mock-service /usr/local/bin \
    && ln -s /pact/bin/pact-provider-verifier /usr/local/bin \
    && ln -s /pact/bin/pact-publish /usr/local/bin \
    && ln -s /pact/bin/pact-stub-service /usr/local/bin

USER jenkins

# Skip initial setup
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
