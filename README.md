# Guide

## Tutorial Source:
- https://www.youtube.com/watch?v=PKcGy9oPVXg&ab_channel=JavaTechie
- https://www.youtube.com/watch?v=vBoOXP6BkDI&ab_channel=JavaTechie

# Steps
1. Install Jenkins
2. Log in Jenkins UI using the initialAdminPassword stored locally. 
Ex: Custom Install location: C:\Users\mydesktopuser\AppData\Local\Jenkins\.jenkins\secrets
3. Create the required credentials (docker, git, jira, etc.)
4. Set the required tools (maven, jdk, etc.) and install required plugins. Plugins to install:
   - Docker Pipeline
   - Kubernetes
   - Git server
5. Create a Pipeline
- Set project git URL
- Set the required triggers (GitHub hook trigger for GITScm polling / others). Should use a Webhooks to trigger deployments on new releases (requires GitHub plugin).
- 