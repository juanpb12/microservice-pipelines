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
                //secret token for gitlab webhook
                secretToken('somestring')
            }
            
            gitlabPush {
                // Trigger that runs jobs on push notifications from GitLab.
                // If set, builds on push events request events.
                buildOnPushEvents()
               
            }
        }
        
    }
}

def tokenGenerator(){
    String alphabet = (('A'..'N')+('P'..'Z')+('a'..'k')+('m'..'z')+('2'..'9')).join() 
    def length = 32
    key = new Random().with {
           (1..length).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
             }
    return key
    }

def buildPipelineJobs() {

    def repoUrl =  repo
    def deployName = jobName 
    def jobDescription = Descipcion
    def jenkinsFileName = jenkinsFileName
    def credentials = git_credentials
    def token = tokenGenerator()


    createDeploymentJob(deployName, repoUrl, jobDescription, jenkinsFileName, credentials)

}

buildPipelineJobs()
