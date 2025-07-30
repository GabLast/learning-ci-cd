# Guide

## Tutorials Sources:
- https://www.youtube.com/watch?v=PKcGy9oPVXg&ab_channel=JavaTechie
- https://www.youtube.com/watch?v=vBoOXP6BkDI&ab_channel=JavaTechie
- https://www.youtube.com/watch?v=rWrshVbvS_U&ab_channel=iQuant
- https://github.com/iQuantC/NodeApp

# Steps
1. Install Jenkins
2. Log in Jenkins UI using the initialAdminPassword stored locally. 
Ex: Custom Install location: C:\Users\mydesktopuser\AppData\Local\Jenkins\.jenkins\secrets
3. Create the required credentials (docker, git, jira, etc.)
4. Set the required tools (maven, jdk, etc.) and install required plugins. Plugins to install:
   - Docker & Docker Pipeline
   - Kubernetes and Kubernetes CLI
   - Git server (maybe not needed)
   - Pipeline Utility Steps (for readMavenPom)
5. Create a Pipeline
   - Set project git URL
   - Set the required triggers (GitHub hook trigger for GITScm polling / others). Should use a Webhooks to trigger deployments on new releases (requires GitHub plugin).
   - Configure the project pipeline script
6. For Kubernetes, for this learning test, Download Kubernetes and Minikube.
Add to path if required and run minikube start. To test:
   - kubectl get po -A
   - kubectl get nodes
   - kubectl cluster-info -> to get the cluster url. Can also leave empty (jenkins will use the kubeconfig server url)
If you restart the cluster, the kubeconfig file will change
Therefore, updating the kubeconfig file on jenkins credentials will be required
7. Setup the Kubernetes Stage in the pipeline:
   - Get cluster info:  kubectl cluster-info
   - Get kube config file in root dir .kube
   - Encode all certs to base64 (remove all line wrappings before doing so) and edit the kubeconfig file
Linux command to this: cat /home/user/.minikube/ca.crt | base64 -w 0; echo
     * Note: Guide said this had to be encoded, but the pipeline failed if I used the encoded file with swapped
     config attributes. However, it worked fine using the original kubeconfig (kubeconfig-original).
     Only had to encode certificate-authority (ca.cert) for the jenkins pipeline syntax
   - On the pipeline syntax, select kubeconfig: Setup Kubernetes CLI
and put all the data from previous points. Also add the original kubeconfig as secret file credentials

# Additional

## GitHub WebHook Integration

1. Add webhook on your repo
2. Set payload as jenkinsurl/github-webhook/
Expose jenkinsurl with a DNS, ngrok or whatever you want
3. Set content type as json
4. Select individual events -> releases and push (for some reason, doesn't work if push is not selected)
5. Set the GitHub hook trigger for GITScm polling on jenkins:

   Default:
   Branch Specifier */main

   For tags only:
   git repository -> advanced -> name: git repo url
   git repository -> advanced -> refspec: '+refs/tags/v*:refs/remotes/origin/tags/v*'
   Branch Specifier -> '**/tags/v*'
   
