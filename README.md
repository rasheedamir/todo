ToDo
====

Service to manage todos!

## ToDos

- [x] Support for a multi-project build
- [x] Usage of the Gradle Wrapper.
- [x] Jackson
- [x] lombok
- [x] Kafka
- [x] Builder Pattern
- [ ] Validation
- [x] MongoDB
- [x] MapStruct
- [x] Generic Exception Handling!
- [ ] Fallback (Hystrix)
- [ ] Rate Limiting
- [ ] Use spring data rest and on save event post to Kafka
- [ ] CheckStyle
- [ ] Lombok Builder for Generic class
- [ ] Search (elasticsearch)
- [ ] Count (cassandra)
- [ ] Implements various testing strategies:

```
1. Unit testing with JUnit.
2. Integration testing against an embedded H2 database.
3. Functional testing with Geb against local or remote web container in Firefox.
4. Smoke testing of a deployed application with HTTPBuilder.

define others ...
```

- [ ] Generates code coverage metrics with JaCoCo.
- [ ] Integrates with Sonar for performing code quality analysis.
- [ ] Publishing to a Maven repository using the Maven Publishing plugin.
- [ ] CheckStyle
- [ ] Gateway
- [ ] Tracing (sleuth + zipkin)
- [ ] Authentication
- [ ] Service Discovery using Consul
- [ ] Logging
- [ ] Limit login attempts
- [ ] Docker
- [ ] Kubernetes
- [ ] Logbook
- [ ] Sleuth (https://github.com/spring-cloud/spring-cloud-sleuth)
- [ ] Java Client

### Consul

- [x] Config management using Consul backed with Git @Ahmad
- [ ] Backing service i.e. mongodb, cassandra, kafka,  etc. discovery through Consul @Ahmad
- [ ] Secrets management using Consul + Vault @Ahmad
- [ ] Gateway to discover services through Consul @Ahmad
- [ ] Developer should be able change configs locally in a file as each developer won't have his own git repo! @Ahmad

### Api Documentation

- [x] Swagger Docs @Rasheed
- [x] Swagger UI @Rasheed
- [ ] Customize the header of the page to include app name instead of link swagger!
- [ ] Fix generic api response codes

### Filters

- [ ] MDC filter to copy request id
- [ ] Request id filter
- [ ] Thread logging filter ( enable log per request on the fly )

### Errors

- [ ] Always return rest response!

### Jackson

- [ ] Indent output
- [ ] Always include null fields in response

### Client

- [ ] Generate Java Client using Swagger API

### Docker

- [x] Build docker image
- [x] Build docker compose files including backing services

### Local Development

- [ ] How to run subset of biz  microservices along with  backing services locally?
- 

### Testing?

- [ ] How to write integration tests?
- [ ] How to run integration tests?
- [ ] E2E? Acceptance Tests != Integration Tests

### Code Quality

- [ ] Sonar
- [ ] CheckStyle
- [ ] Jacoco - Code Coverage http://www.eclemma.org/jacoco/

### Rest Errors

- [ ] Return harmonized rest errors
- [ ] 

### ascii-doc

- [ ] Using swagger generate ascii-doc which can be referenced in the README
- [ ] 

### Logging

- [ ] Logbook
- [ ] Log response times IN & OUT
- [ ] MDC

## Environment Variables

Please set following environment variables:

1. MONGODB_HOST
2. MONGODB_PORT
3. KAFKA_CLUSTER
4. ELASTICSEARCH_CLUSTER

## Components

Here are the list of components (microservices):

1. reminder/reminder - send reminders when a reminder is due
2. search - search todos
3. gateway - edge service
4. store - to store todos in mongodb for CRUD operations
5. 

## Backing Services

1. MongoDB
2. Kafka
3. ElasticSearch
4. Cassandra
5. Consul

