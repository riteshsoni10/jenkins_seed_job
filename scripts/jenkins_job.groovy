pipelineJob('web_application_deployment'){
    description('Web Application Deployment using Jenkins Pipeline')
    properties {
        githubProjectUrl('https://github.com/riteshsoni10/demo_website.git')
    }
    definition {
        triggers {
             githubPush()
        }
       cpsScm {
            scm {
                git{
                    remote {
                        url('https://github.com/riteshsoni10/demo_website.git')
                    }
                    branch('*/master')     
                }
            }
            lightweight()
        }
    }
}
