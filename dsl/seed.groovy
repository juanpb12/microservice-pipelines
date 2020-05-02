def createDeploymentJob(jobName, repoUrl, jobDescription, jenkinsFileName, cred, secretToken) {
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
                //secret token for gitlab webhook
                secretToken(secretToken)
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
    def secretToken = print new Random().with {(1..32).collect {(('a'..'z')).join()[ nextInt((('a'..'z')).join().length())]}.join()}


    createDeploymentJob(deployName, repoUrl, jobDescription, jenkinsFileName, credentials, secretToken)

}

buildPipelineJobs()
