# Library System

Build an online library system using JAVA, SpringBoot Framework, and third-party serivces such as AWS S3 and AWS SQS.

## Building Library System

Deploy PostgreSQL on a Docker container

	docker pull postgres
	docker run 
	-name [container_name] 
	-e POSTGRES_DB=[server_name] 
	-e POSTGRES_USER=[admin] 
	-e POSTGRES_PASSWORD=[password]
	-p 5433:5432 
	-d postgres

Use Flyway database migration tool to create your database schema.

	mvn flyway:clean -Ddb_url=[DATABASE_URL]:[PORT_NUMBER]/[DATABASE_NAME] -Ddb_username=[DATABASE_USERNAME] -Ddb_password=[DATABASE_PASSWORD]
	mvn flyway:migrate -Ddb_url=[DATABASE_URL]:[PORT_NUMBER]/[DATABASE_NAME] -Ddb_username=[DATABASE_USERNAME] -Ddb_password=[DATABASE_PASSWORD]
  
Set your environment variables in your IDE

```bash
  -Ddatabase.driver=org.postgresql.Driver
  -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect
  -Ddatabase.url=jdbc:postgresql://[LOCALHOST_URL]
  -Ddatabase.user=[DATABASE_USERNAME]
  -Ddatabase.password=[DATABASE_DRIVER]
  -Dlogging.level.org.springframework=INFO
  -Dlogging.level.com.ascending=TRACE
  -Dsecret.key=[SECRETKEY]
  -Daws.accessKeyId=[AWS_ACCESSKEYID]
  -Daws.secretKey=[AWS_SECRETKEY]
```

Pull the latest Maven image in docker and run it

```bash
  docker pull maven:3.6.0-jdk-8
  docker run -it maven:3.6.0-jdk-8 /bin/bash
```

Find this container's internal IP address

```bash
  docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' ${CONTAINER_ID}
```

Create your database schema within this container and make sure all of your unit tests are successful

```bash
  mvn clean compile flyway:migrate -Ddb_url=[LOCALHOST_URL]:[PORT_NUMBER]/[DATABASE_NAME] -Ddb_username=[USERNAME] -Ddb_password=[PASSWORD]
  
  mvn test -Ddatabase.driver=org.postgresql.Driver -Ddatabase.dialect=org.hibernate.dialect.PostgreSQL9Dialect -Ddatabase.url=jdbc:postgresql://[LOCALHOST_URL]:[PORT_NUMBER]/[DATABASE_NAME] -Ddatabase.user=[USERNAME] -Ddatabase.password=[PASSWORD] -Dlogging.level.org.springframework=INFO -Dlogging.level.com.ascending=INFO -Dsecret.key=[SECRET_KEY] -Daws.accessKeyId=[AWS_ACCESSKEYID] -Daws.secretKey=[AWS_SECRETKEY]
```

Take the compiled code and package it in its distributable format, such as a JAR.

```bash
  mvn compile package -DskipTests=true -q
```



## A few more things concerning URI :

There is a health check URI '/healthcheck' in this project that can bypass the whole authorization & authentication process to make sure the server is up and running. If you want to gain access to a target URI which is not the health check URI, however, the first thing you need to do is to get a new JSON Web Token from "/auth" URI. Once you have that, you can add that to your API requests and the server will respond to your request based on your authorization.

By default, the manager user has the access to all APIs, details are listed below :

```bash
Name : 'rhang'
Password : '25f9e794323b453885f5181f1b624d0b'
First Name : 'Ryo'
Last Name : 'Hang'
Email : 'rhang@ascending.com'
```




