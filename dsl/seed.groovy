def createDeploymentJob(jobName, repoUrl, jobDescription, jenkinsFileName, cred) {
    pipelineJob(jobName) {
        
        description(jobDescription)
        
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(repoUrl)
                            credentials(cred)
                        }
                        branches('testing')
                        extensions {
                            cleanBeforeCheckout()
                        }
                    
                    }
                scriptPath(jenkinsFileName)
                }
            }
        }
        
        triggers {
            
            gitlab {
                secretToken('7483bf1d951c4421a5947268c69f874b')
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
    def jenkinsFileName = jenkinsFileName
    def credentials = git_credentials

    createDeploymentJob(deployName, repoUrl, jobDescription, jenkinsFileName, credentials)

}

buildPipelineJobs()
