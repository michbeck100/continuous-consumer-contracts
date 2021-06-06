FROM jenkins/jenkins:lts-alpine

ENV TZ="Europe/Berlin"

# Install required plugins
RUN /usr/local/bin/install-plugins.sh git:4.7.2 blueocean:1.24.7 job-dsl:1.77

USER root

ARG PACT_VERSION=1.88.54

RUN curl -LO https://github.com/pact-foundation/pact-ruby-standalone/releases/download/v$PACT_VERSION/pact-$PACT_VERSION-linux-x86_64.tar.gz \
    && tar xzf pact-$PACT_VERSION-linux-x86_64.tar.gz \
    && rm -f pact-$PACT_VERSION-linux-x86_64.tar.gz

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
