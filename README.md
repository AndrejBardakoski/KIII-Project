
# DevOps project: Dockerization Orchestration and CI/CD pipeline of Sprig Boot web app
## Intro

In this project we will dockerize, orchestrate and create CI/CD pipeline for "BalloonApp". The app is a simple Spring Boot web app that use PostgreSQL as database.

## Dockerization and Orchestration with docker-compose

### [Dockerfile](https://github.com/AndrejBardakoski/KIII-Project/blob/main/Dockerfile)

### [docker-compose.yml](https://github.com/AndrejBardakoski/KIII-Project/blob/main/docker-compose.yml)

## Kubernetes Orchestration

### [K8S manifests](https://github.com/AndrejBardakoski/KIII-Project/tree/main/K8S%20manifests)

## CI
we use github actions to create continues integration pipeline that on every push on github a Dockerfile of the app is created and pushed to DockerHub

## CD
For the continuous delivery pipeline we use the ArgoCD tool, set up in a way to make sure our desired state of the app (described in the manifests directory) to match the actual state (in our local Kubernetes cluster)

 **[argocdproj / application.yaml](https://github.com/AndrejBardakoski/KIII-Project/blob/main/application.yaml)**