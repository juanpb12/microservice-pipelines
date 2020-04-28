def createDeploymentJob(jobName, repoUrl) {
    pipelineJob(jobName) {
        
        description(jobDescription)
        
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
        
        authenticationToken('66829b0aaf22638f8094947e6cdc8188')
        
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

    def repoUrl =  repo
    def deployName = jobName 
    def jobDescription = Descipcion

    createDeploymentJob(deployName, repoUrl, jobDescription)

}

buildPipelineJobs()
