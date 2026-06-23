# Spring Boot EKS CI/CD Project 🚀

## Project Overview

This project demonstrates a complete CI/CD pipeline for deploying a Spring Boot application to Amazon EKS using Jenkins, Docker, SonarQube, GitHub Webhooks, and Kubernetes.

Any code push to GitHub automatically triggers Jenkins, builds the application, performs code quality analysis, creates a Docker image, pushes it to Docker Hub, and deploys the latest version to AWS EKS.

---

## Architecture

```text
Developer
    ↓
GitHub Repository
    ↓ (Webhook)
Jenkins Pipeline
    ↓
Maven Build
    ↓
SonarQube Analysis
    ↓
Docker Image Build
    ↓
Docker Hub Push
    ↓
AWS EKS Deployment
    ↓
Kubernetes Service (LoadBalancer)
    ↓
Application Access
```

---

## Technologies Used

### Cloud

* AWS EKS
* AWS EC2
* AWS IAM
* AWS VPC

### DevOps Tools

* Jenkins
* SonarQube
* Docker
* Docker Hub
* GitHub Webhooks

### Container Orchestration

* Kubernetes
* Horizontal Pod Autoscaler (HPA)

### Application

* Java 21
* Spring Boot 3
* Maven

### Infrastructure as Code

* Terraform

---

## Features

* Automated CI/CD pipeline
* GitHub Webhook Integration
* Maven Build Automation
* SonarQube Code Analysis
* Docker Image Creation
* Docker Hub Image Push
* Kubernetes Deployment
* LoadBalancer Service Exposure
* Horizontal Pod Autoscaling
* Infrastructure Provisioning using Terraform

---

## Jenkins Pipeline Stages

### Checkout

Clones the latest code from GitHub.

### Build

Builds the Spring Boot application using Maven.

```bash
mvn clean package
```

### SonarQube Scan

```bash
mvn sonar:sonar
```

### Docker Build

```bash
docker build -t vamsi0497/springboot:${BUILD_NUMBER} .
```

### Docker Push

Pushes image to Docker Hub.

```bash
docker push vamsi0497/springboot:${BUILD_NUMBER}
```

### Configure EKS

```bash
aws eks update-kubeconfig \
--region us-east-1 \
--name devops-eks
```

### Deploy to EKS

```bash
kubectl apply -f kubernetes/
```

### Verify Deployment

```bash
kubectl get pods -n devops
kubectl get svc -n devops
kubectl get deployments -n devops
```

---

## Kubernetes Components

### Namespace

Creates isolated environment.

### ConfigMap

Stores application configuration.

### Deployment

Manages application pods.

### Service

Exposes application using AWS Load Balancer.

### HPA

Automatically scales pods based on CPU usage.

---

## Terraform Resources

* VPC
* Public Subnets
* Private Subnets
* Internet Gateway
* NAT Gateway
* EKS Cluster
* Managed Node Group
* Security Groups
* IAM Roles

---

## Application Access

After deployment:

```bash
kubectl get svc -n devops
```

Access the application using the LoadBalancer URL.

Example:

```text
http://<load-balancer-dns>
```

---

## Project Structure

```text
springboot-eks-cicd-project
│
├── src/
├── kubernetes/
│   ├── namespace.yml
│   ├── configmap.yml
│   ├── deployment.yml
│   ├── service.yml
│   └── hpa.yml
│
├── terraform/
│   ├── main.tf
│   ├── variables.tf
│   └── outputs.tf
│
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

---

## Key Achievements

✅ Built end-to-end CI/CD Pipeline

✅ Automated deployments using GitHub Webhooks

✅ Integrated SonarQube for code quality

✅ Containerized Spring Boot application

✅ Deployed application on AWS EKS

✅ Implemented Kubernetes HPA

✅ Provisioned infrastructure using Terraform

---

## Author

**Telugu Vamsi**

Site Reliability Engineer (SRE)

AWS | Kubernetes | Jenkins | Terraform | Docker | Prometheus | Grafana
