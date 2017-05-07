ToDo
====

Service to manage todos!

## ToDos

### Version 1

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

### Version 1.5

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
- [ ] Swagger Docs
- [ ] Swagger UI
- [ ] CheckStyle
- [ ] Gateway
- [ ] Tracing (sleuth + zipkin)
- [ ] Authentication
- [ ] Service Discovery with Consul
- [ ] Logging
- [ ] Limit login attempts
- [ ] Docker
- [ ] Kubernetes
- [ ] 

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