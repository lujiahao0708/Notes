# DockerfileCollection

Commonly used dockerfiles

## Pre

1. Install Docker first.
2. Get this collection
	
		$ git clone https://github.com/chiahaolu/DockerfileCollection.git
		$ cd DockerfileCollection

## Usage

### 1.JDK
> Use url in `jdk_download.txt` to download the jdk,and save jdk with dockerfile in the same folder.

Bulid a new image:

	$ cd jdk1.7
	$ docker build -t="lujiahao/jdk1.7" .
#### Check

Start a temporary container to check jdk:

	docker run --rm -it lujiahao/jdk1.7 /bin/bash

Then check jdk in three steps:
	
	java
	javac
	java -version

Now,JDK image is OK!

### 2.Tomcat
Bulid a new image:

	$ cd tomcat
	$ docker build -t="lujiahao/tomcat7" .
#### Check
Start a temporary container to check tomcat:

	docker run --name tomcat7 -p 8080:8080 -d lujiahao/tomcat7 ./catalina.sh run

type `http://192.168.2.30:8080` in brower to check.
