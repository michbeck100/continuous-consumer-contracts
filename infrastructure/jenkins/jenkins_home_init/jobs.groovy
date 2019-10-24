def gitUrl = 'file:///continuous-consumer-contracts'

['movie-info-service', 'ratings-data-service', 'movie-catalog-service'].each {
    def app = it
    multibranchPipelineJob("$app") {
        branchSources {
            git {
                id("$app") // IMPORTANT: use a constant and unique identifier
                remote(gitUrl)
            }
        }
        orphanedItemStrategy {
            discardOldItems {
                numToKeep(5)
            }
        }
        factory {
            workflowBranchProjectFactory {
                scriptPath("$app/Jenkinsfile")
            }
        }
    }
}
// Provider job that only executes contract tests, usually triggered by webhook
['movie-info-service', 'ratings-data-service'].each {
    def app = it
    pipelineJob("$app-provider") {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(gitUrl)
                        }
                        branch("master")
                        extensions {}
                    }
                }
                scriptPath("$app/Jenkinsfile-provider")
            }
        }
    }
}

