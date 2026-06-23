# Spring Boot EKS CI/CD Project

## Overview

This project demonstrates an end-to-end CI/CD pipeline using:

* Spring Boot
* Jenkins
* SonarQube
* Docker
* DockerHub
* AWS EKS
* Terraform
* Kubernetes
* GitHub Webhooks

## CI/CD Flow

GitHub → Jenkins → SonarQube → Docker Build → DockerHub → EKS → LoadBalancer

## Build

```bash
mvn clean package
```

## Run

```bash
mvn spring-boot:run
```

## Docker

```bash
docker build -t vamsi0497/springboot:latest .
docker push vamsi0497/springboot:latest
```

## Deploy

```bash
kubectl apply -f kubernetes/
```

## Verify

```bash
kubectl get pods -n devops
kubectl get svc -n devops
```

# webhook test
