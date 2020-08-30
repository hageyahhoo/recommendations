# recommendations
This repository was cloned from https://github.com/metflix/recommendations with following extensions:
- Divided components for easily understand each responsibility
- Added unit tests by using Mocks

<br>


## How to run this service

### 1) CUI
1. Run `./mvnw clean package` and `target/recommendations-0.0.1-SNAPSHOT.jar` will be created
2. Run `java -jar target/recommendations-0.0.1-SNAPSHOT.jar`

<br>

### 2) IDE (e.g. IntelliJ IDEA)
Run [src/main/java/com/metflix/RecommendationsApplication.java](https://github.com/hageyahhoo/recommendations/blob/master/src/main/java/com/metflix/RecommendationsApplication.java)

<br>


## How to run tests
Run `./mvnw clean build`

<br>


## How to run this service as a Docker container

### 1) Setup
1. Run `docker network create msa`
2. Run `docker build -t recommendations:0.0.1 .`

<br>

### 2) Start
1. Run `docker run --name recommendations --network msa -d -p 3333:3333 recommendations:0.0.1`
2. Access to `http://localhost:3333/api/recommendations/making`

<br>

### 3) Stop
1. Run `docker stop <container_id>`
2. Run `docker rm recommendations`
