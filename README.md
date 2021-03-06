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
- [ ] Logging
- [ ] Limit login attempts
- [ ] Docker
- [ ] Kubernetes
- [ ] Logbook
- [ ] Sleuth (https://github.com/spring-cloud/spring-cloud-sleuth)

### Consul

- [x] Config management using Consul backed with Git @Ahmad
- [ ] Backing service i.e. mongodb, cassandra, kafka,  etc. discovery through Consul @Ahmad
- [ ] Secrets management using Consul + Vault @Ahmad
- [ ] Gateway to discover services through Consul @Ahmad
- [ ] Developer should be able change configs locally in a file as each developer won't have his own git repo! @Ahmad
- [ ] Access individual API's swagger docs through gateway @Ahmad

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

- [ ] Generate Java Client using Swagger API (hystrix, observables, etc.)

### Docker

- [x] Build docker image
- [x] Build docker compose files including backing services

### Local Development

- [ ] How to run subset of biz  microservices along with  backing services locally?
- 

### Testing?

- [x] Unit Tests
- [ ] Component Tests
- [ ] How to write integration tests?
- [ ] How to run integration tests?
- [ ] E2E? Acceptance Tests != Integration Tests
- [ ] How to avoid polluting the backingservie (e.g. any biz service) with test data? Who sld clean it up?
- [ ] CDC - Consumer Driven Contracts
- [ ] Synthetic Transactions

### Code Quality

- [ ] Sonar
- [ ] CheckStyle
- [ ] Jacoco - Code Coverage http://www.eclemma.org/jacoco/

### Rest Errors

- [ ] Return harmonized rest errors
- [ ] 

e.g.

```
NOT OK - when zuul fails to connect with the service
{
  "httpCode": 404,
  "message": "Not found"
}
```

### ascii-doc

- [ ] Using swagger generate ascii-doc which can be referenced in the README
- [ ] 

### Logging

- [ ] Logbook
- [ ] Log response times IN & OUT
- [ ] MDC
- [ ] Add request id in every log
- [ ] Each log statement should include: 
[app-name][environment-name][request-id][build-number][]
http://across.foreach.be/docs/across-standard-modules/ApplicationInfoModule/1.0.1.RELEASE/reference/
- [ ] Sleuth? See how it can help in tracing...
- [ ] Ensure request id is copied over when microservices are communication of REST or QUEUE/TOPIC
- [ ] 

instanceId that combines application, environment and hostname into a single String that uniquely identifies the application instance.

### Kafka

- [ ] Enable debug logging

### Monitoring - @Waseem

- [ ] Measure & record API response times
- [ ] Grafana graph to display average response times per api call
- [ ] ZMon alert when average response time goes down below a certain limit
- [ ] ZMon alert if number of servers reduce to 0 for any service
- [ ] Vizceral

### Documentation

- [ ] Generate live docs using asciidoc
- [ ] 

### Load Testing

- [ ] 

### Metrics

- [ ] Measure & store metrics

### Client

- [ ] Generate Java Client http://nebula-plugins.github.io/documentation/introduction_to_nebula.html#how-netflix-builds-software

### Audit

- [ ] Enable auditing

### Starter Auto Configs

- [ ] ResponseTimeFilter
- [ ] ThreadLoggingFilter
- [ ] Jwt Security
- [ ] Customized Error Response
- [ ] 

### Health Checks

- [ ] Aggregated health checks (including attached backing resources)

### Builder

- [ ] Default values for collections
- [ ] @Singular annotation for collections to add method to add single value as well

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

