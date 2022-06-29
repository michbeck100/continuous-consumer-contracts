FROM jenkins/jenkins:jdk17

ENV TZ="Europe/Berlin"

# Install required plugins
RUN /usr/local/bin/install-plugins.sh git:4.11.3 blueocean:1.25.5 job-dsl:1.79

USER root

RUN apt-get update && \
    apt-get install -y ruby && \
    rm -rf /var/lib/apt/lists/* && \
    gem install pact_broker-client

USER jenkins

# Skip initial setup
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false -Dhudson.security.csrf.GlobalCrumbIssuerConfiguration.DISABLE_CSRF_PROTECTION=true
