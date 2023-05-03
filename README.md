# trafiklab-demo-app

The following was discovered as part of building this project:

* The JVM level was changed from '1.8' to '17', review
  the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)
  on the wiki for more details.

# Getting Started

### Building the project

```
cd trafiklab-demo-app
```

#### Unix based system

```
./mvnw clean install
```

#### Windows

```
mvnw.cmd clean install
```

* If we don't have the specified Maven in the wrapper properties, it'll be downloaded and installed in the folder
  $USER_HOME/.m2/wrapper/dists of the system.

### Running the project

#### Using Spring-Boot

```
./mvnw spring-boot:run
```

* The application will run on default port 8080, you can define another port by running the following

```
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8000
```

#### Using Docker

* Docker build

```
docker build -t trafiklab-demo-app .
```

* Docker run

```
docker run -p 8080:8080 trafiklab-demo-app
```

### Reference Documentation

Trafiklab API

* [SL Stops and lines v2.0 (Hållplatser och linjer 2)](https://www.trafiklab.se/api/trafiklab-apis/sl/stops-and-lines-2)

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

