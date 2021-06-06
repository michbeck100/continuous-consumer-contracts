# Infrastructure

This folder contains everything to set up a pact broker (with postgres) and
a Jenkins with all consumer and provider jobs. The jobs are created with the [Job DSL plugin](https://jenkinsci.github.io/job-dsl-plugin/)

Prerequisites:
- docker-compose

Steps:
- ``cd infrastructure``
- ``cp -r jenkins/jenkins_home_init jenkins/jenkins_home``
- ``docker-compose up``

You should now be able to access Jenkins on <http://localhost:9091> and the Pact Broker on <http://localhost:9090>.

The Jenkins contains:
- run-contract-tests job for provider that only executes the contract tests
- build-and-deploy job for consumer and provider
- branch build jobs for consumers

To access your repository you might have to add your own credentials to the jenkins instance. 
For this example the credentials have the id `GitHub`. 
