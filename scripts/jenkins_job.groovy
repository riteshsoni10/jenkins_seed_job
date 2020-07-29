def repo_url=getBinding().getVariables()['GIT_URL']


pipelineJob('test-env'){
    description('Web Application Deployment in Test Environment using Jenkins Pipeline')
    properties {
        githubProjectUrl("$repo_url")
    }
    definition {
        triggers {
             scm(* * * * *)
        }
       cpsScm {
            scm {
                git{
                    remote {
                        url("$repo_url")
                    }
                    branch('*/develop') 
                    extensions {
                        userIdentity {
                            // If given, "git config user.name [this]" is called before builds.
                            name("deployment_server")
                            // If given, "git config user.email [this]" is called before builds.
                            email("jenkins@organisation.com")
                        } 
                    }    
                }
            }
            lightweight()
        }
    }
}

pipelineJob('prod-env'){
    description('Web Application Deployment in Production Environment using Jenkins Pipeline')
    properties {
        githubProjectUrl("$repo_url")
    }
    definition {
        triggers {
            //githubPush()
            scm(* * * * *)
        }
       cpsScm {
            scm {
                git{
                    remote {
                        url("$repo_url")
                    }
                    branch('*/master')
                    extensions{
                        userIdentity {
                            // If given, "git config user.name [this]" is called before builds.
                            name("deployment_server")
                            // If given, "git config user.email [this]" is called before builds.
                            email("jenkins@organisation.com")
                        } 
                    }     
                }
            }
            lightweight()
        }
    }
}


