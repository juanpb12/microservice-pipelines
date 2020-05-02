def createDeploymentJob(jobName, repoUrl, jobDescription, jenkinsFileName, cred, randomString) {
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
                secretToken(randomString)
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
    de charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()
    def randomString = RandomStringUtils.random(32, charset.toCharArray())

    createDeploymentJob(deployName, repoUrl, jobDescription, jenkinsFileName, credentials, randomString)

}

buildPipelineJobs()
