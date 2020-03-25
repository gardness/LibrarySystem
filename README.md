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



## Request & Response Examples

### API Resources

  - [GET /healthcheck] (#get healthcheck info)
  - [GET /auth] (#get a new JSON Web Token)
  
  - [GET /books] (#get the current book list)
  - [GET /books/{$bookTitle}] (#get info on the book with a specific book title)
  - [PUT /books/{$bookId}] (#update info on the book with a specific book ID)
  - [POST /books] (#create info on a new book)
  - [DEL /books/{$bookTitle}] (#delete info on the book with a specific book title)
  
  - [GET /customers/{$customerName}] (#get info on the book with a specific book title)
  - [PUT /customers/{$customerId}] (#update info on the book with a specific book ID)
  - [POST /books] (#create info on a new book)
  - [DEL /books/{$bookTitle}] (#delete info on the book with a specific book title)
  
  - [POST /magazines/[id]/articles](#post-magazinesidarticles)
  /books/Thinking in Java

### GET /magazines

Example: http://example.gov/api/v1/magazines.json

Response body:

    {
        "metadata": {
            "resultset": {
                "count": 123,
                "offset": 0,
                "limit": 10
            }
        },
        "results": [
            {
                "id": "1234",
                "type": "magazine",
                "title": "Public Water Systems",
                "tags": [
                    {"id": "125", "name": "Environment"},
                    {"id": "834", "name": "Water Quality"}
                ],
                "created": "1231621302"
            },
            {
                "id": 2351,
                "type": "magazine",
                "title": "Public Schools",
                "tags": [
                    {"id": "125", "name": "Elementary"},
                    {"id": "834", "name": "Charter Schools"}
                ],
                "created": "126251302"
            }
            {
                "id": 2351,
                "type": "magazine",
                "title": "Public Schools",
                "tags": [
                    {"id": "125", "name": "Pre-school"},
                ],
                "created": "126251302"
            }
        ]
    }

### GET /magazines/[id]

Example: http://example.gov/api/v1/magazines/[id].json

Response body:

    {
        "id": "1234",
        "type": "magazine",
        "title": "Public Water Systems",
        "tags": [
            {"id": "125", "name": "Environment"},
            {"id": "834", "name": "Water Quality"}
        ],
        "created": "1231621302"
    }







