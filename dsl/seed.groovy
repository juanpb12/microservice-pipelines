def createDeploymentJob(jobName, repoUrl, jobDescription) {
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
        
        triggers {
            
            gitlab {
                secretToken('foo')
            }
            
            gitlabPush {
                // Trigger that runs jobs on push notifications from GitLab.
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
