#### Introduction

This is a simple REST mock server written with Java Spring Boot 3.0.1 and Java 17.

It provides sample endpoints to render output either based on static resource file or simple logic as defined in the service classes.

The server can either be brought up as a standalone jar application or as a docker.

Feel free to provide feedback or feature request.


#### Customize the Mock Service

I tried to keep it simple with the controller and service class implementation so that one can easily modify the endpoint name, parameter, service logic as well
as the return response from the static file.


#### Available Endpoints:


<details>
<summary>/resource?data=[value]</summary>

This mock service returns static response from the file located in `src/main/resources/data`


_Sample 1_

**curl "http://localhost:9090/resource?data=100"**


will return 

```console
[
 {"id":"100","name":"jack","age":"30"},
 {"id":"101","name":"jill","age":"32"}
]
```

_Sample 2_

**curl "http://localhost:9090/resource?data=200"**


will return

```console
[
 {"id":"200","name":"tom","age":"40"},
 {"id":"201","name":"jerry","age":"28"}
]
```

When request with a non existing file, the response will be reading from file `empty`

_Sample 3_

**curl "http://localhost:9090/resource?data=300"**


will return

```console
[]
```
</details>

<details>
<summary>/status?code=[value]</summary>

_Sample 1_


**curl "http://localhost:9090/status?code=200"**


will return status code 200 and the following response

```console
OK
```

_Sample 2_

**curl "http://localhost:9090/status?code=400"**


will return status code 400 and the following response

```console
400 BAD_REQUEST
```

</details>

<details>
<summary>/delay?code=[value]</summary>

_Sample 1_


**curl "http://localhost:9090/delay?ms=3000"**


will return response with 3000 milliseconds delay

```console
Response with delay of 3000 milliseconds
```
</details>

#### Build and run
```console
mvn clean install
java -jar target/tnite-mock-1.0-SNAPSHOT.jar
```
#### Build and run with docker
```console
mvn clean install && docker build -t tnite-mock:latest .
docker run -p 9090:9090 tnite-mock:latest
```
