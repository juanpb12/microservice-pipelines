def createDeploymentJob(jobName, repoUrl) {
    pipelineJob(jobName) {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(repoUrl)
                        }
                        branches('testing')
                        extensions {
                            cleanBeforeCheckout()
                        }
                    
                    }
                scriptPath("Jenkinsfile")
                }
            }
        }
        triggers {
            
            // Trigger that runs jobs on push notifications from GitLab.
            gitlabPush {

                // If set, builds on push events request events.
                buildOnPushEvents()
               
            }
        }
        
    }
}


def buildPipelineJobs() {

    def repoUrl =  "https://github.com/juanpb12/docker-poc.git"
    def deployName = jobName 


    createDeploymentJob(deployName, repoUrl)

}

buildPipelineJobs()
