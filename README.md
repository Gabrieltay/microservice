# GPLS Batch Processing 
GPLS Batch Processing will perform scheduling jobs that are triggered by an external cron service (eg. [dkron](https://dkron.io/)).

Jobs to run
- SFTP File transfer between GPLS and external agencies (eg. ICA)
- Transfer Payment file transfer between GPLS and bank
- Email Sending 
- Email Polling

## Prerequisites
- Java 8 (Require a later version than 1.8.0_161, to include [JCE](https://www.oracle.com/technetwork/java/javase/downloads/jce-all-download-5170447.html) by default. Java 10 does not work)
- Gradle

## Build
```bash
$ ./gradlew build
```

## Run
This will start the batch application and other services locally
```bash
# Runs Batch application and other services locally
$ docker-compose -f docker-compose.local.yml
```

## Test
```bash
$ ./gradlew check
```



### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)

### Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
