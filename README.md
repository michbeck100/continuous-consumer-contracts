# Continuous consumer contracts
This repository constains an example for the continuous consumer contracts session by [Michael Kotten](https://www.michaelkotten.com/vortraege/).

## Setup

Once you checkout this repository, you can start the infrastructure part, which consists of a jenkins, pact broker and postgresql instance, by using `docker-compose`. See the instructions [here](infrastructure/README.md).

During startup of jenkins three multi-stage builds will be created, one for every sample application. 

## Running the tests

Start the build job that runs the consumer tests (`movie-catalog-service`). This will create the first pacts and upload them to the pact broker at http://localhost:9090. 
Once the first pacts are uploaded, you can create the webhooks for the provider jobs by running [create-hooks.sh](infrastructure/webhooks/create-hooks.sh). 
If you start the consumer build job again, it should now trigger the builds of the provider jobs, too. 

### Credits

Most of this wasn't possible without the blog post from kreuzwerker: https://kreuzwerker.de/post/introduction-to-consumer-driven-contract-testing
