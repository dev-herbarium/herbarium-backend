# Read Me First

The following was discovered as part of building this project:

* The original package name 'dev.gml.herbarium-backend' is invalid and this project uses 'dev.gml.herbarium_backend' instead.

## Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.6/maven-plugin/build-image.html)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/3.5.6/reference/testing/testcontainers.html#testing.testcontainers)
* [Testcontainers MySQL Module Reference Guide](https://java.testcontainers.org/modules/databases/mysql/)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.6/reference/web/servlet.html)
* [Spring Security](https://docs.spring.io/spring-boot/3.5.6/reference/web/spring-security.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.6/reference/using/devtools.html)
* [Testcontainers](https://java.testcontainers.org/)
* [Docker Compose Support](https://docs.spring.io/spring-boot/3.5.6/reference/features/dev-services.html#features.dev-services.docker-compose)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Docker Compose support

This project contains a Docker Compose file named `compose.yaml`.
In this file, the following services have been defined:

* mysql: [`mysql:latest`](https://hub.docker.com/_/mysql)

Please review the tags of the used images and set them to the same as you're running in production.

### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/3.5.6/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

* [`mysql:latest`](https://hub.docker.com/_/mysql)

Please review the tags of the used images and set them to the same as you're running in production.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## 📝 gml notes

### 🐳 [Docker](https://docs.docker.com/compose/)

Clean Docker Compose & Maven and Restart Everything

1. Stop Docker: `docker-compose down`
2. Remove the volume to start fresh: `docker volume rm herbarium-backend_mysql_data`
3. Check if any containers are still running: `docker ps`
4. Clean Maven build: `./mvnw clean`
5. Start Docker: `docker-compose up -d`
6. Wait for MySQL to be ready, then start Spring Boot with MySQL profile: `./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=mysql"`

### 🛢️ [MySQL](https://www.mysql.com/)

Verify Connection

1. Connect to MySQL: `mysql -h 127.0.0.1 -P 3307 -u root -p verysecret`
2. Check if tables were created:
    * `USE herbarium_db;`
    * `SHOW TABLES;`

### 📊 [JaCoCo](https://www.eclemma.org/jacoco/)

1. Run Test & see Test Coverage

    ```bash
    ./mvnw test jacoco:report
    ```

### 🧩 [Swagger UI](https://swagger.io/)

Run Swagger UI

1. Start SpringBoot

    ```bash
    ./mvnw spring-boot:run
    ```

2. Go to 👉 <http://localhost:8080/swagger-ui/index.html>

### 📕 [JavaDoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)

Commands for generate JavaDoc documentation

1. To generate the JavaDoc JAR (in `target/`):

    ```bash
    mvn clean package
    ```

2. To generate the full HTML site (in `target/site/apidocs/index.html`):

    ```bash
    mvn javadoc:aggregate
    ```
