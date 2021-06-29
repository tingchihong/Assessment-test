# Assessment-test

Primary goals of the assessment are:
* Ease of maintainability and readability
* Expose API for Client (Example: to be test via Postman. Please provide the Postman Collection.)
* Each api required to log REQUEST & RESPONSE info into logs file.
* Connect to database, preferred with MSSQL database
* @transactional is required implement in the project.
* GET method API with Pagination
* Expose API nested calling another API from 3rd party.


This is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/).
You can build a jar file and run it from the command line:


```
git clone https://github.com/tingchihong/Assessment-test.git
cd Assessment-test
./mvnw package
java -jar target/*.jar
```
Please note that the database needs to be online before the application build process.
Check database configuration below to start the database server and create the database.

## Database configuration
You could serve MsSql locally with docker:

```
docker run -d --name Test -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=<password>' -p 1433:1433 microsoft/mssql-server-linux
```
Access to MsSql server and create database
(database name TESTDB used as default in the project, you may edit it in application.properties):
```
mssql -u sa -p <password>
create database <database_name>
```

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer (full JDK not a JRE).
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, just follow the install process here: https://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
  * [VS Code](https://code.visualstudio.com)
  
## API exposed
refer [Postman Collection](https://www.getpostman.com/collections/cefd2cc2b63c93d3a763)

The APIs exposed are using different type of HTTP methods, request parameters and responses type as a sample with different code implementations.

HTTP methods used: 
* GET
* POST
* PUT

Request Parameter types used:
* Path Parameter
* Request Parameter
* Request Body (Raw string with JSON format)
* Http Servlet Request

Response types used:
* Http Servlet Response
* Response Body

### HTTP Request and Response Logging
AspectJ is used for HTTP request and response data logging in this project.


