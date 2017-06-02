todo store
==========

* stores todos in mongodb
* posts them on kafka for others to listen

Service Registration in Consul

```
2017-05-11 23:07:15.799  INFO 1950 --- [           main] o.s.c.c.s.ConsulServiceRegistry          [register            ] Registering service with consul: NewService{id='store-5001', name='store', tags=[contextPath=/store], address='192.168.1.4', port=5001, enableTagOverride=null, check=Check{script='null', interval=10s, ttl=null, http=http://192.168.1.4:5001/health, tcp=null, timeout=null, deregisterCriticalServiceAfter=null}}
```

Actuator
========

https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-endpoint-hypermedia

Actuator endpoints allow you to monitor and interact with your application.
```
/actuator # Provides a hypermedia-based “discovery page” for the other endpoints. Requires Spring HATEOAS to be on the classpath.
```

Externalized configuration
==========================
The application stack uses a consul config loader (wrapper for git2consul) and Spring Cloud's consul config,
 to read and get configuration from a git repo and load into Consul, and then update in the Spring environment.
 Within a Spring project the bootstrap.yml should be updated with the consul config properties. This will use consul
 to load configuration properties as well as watch consul for changes to these properties.

```yaml
 spring:
     cloud:
         consul:
             host: localhost
             port: 8500
             config:
                 format: KEY_VALUE
                 watch:
                     enabled: true
```

Configuration can be externalized in a Git repository. Each configuration property should be added as a separate file.
 The file name should be kept the same as the expected property, e.g. "server.port". The Consul config loader
 can be used as a docker container for example. The public image is available on the docker hub: stakater/consul-config-loader
 The container should be provided a git2consul config file, example [here](../config/git2consul.json) as a volume mapping.
 The name property of the repos defined in git2consul.json follows the convention expected by the spring apps i.e. "config/<spring-app-name>"
 The following environment variables should also be passed to the container:
  * `CONFIG_MODE=git`
  * `CONSUL_URL=`
 
Setting up Spring cloud consul config, and the Consul config loader will update the Spring Environment whenever a  
 configuration property is added/updated/deleted, whether in git, or directly in consul. However for the updated property
 be loaded in a Spring component, the `@RefreshScope` annotation should be used on the bean.


Backing Services configuration
==============================
The docker registrator is used to register all services being run in docker, into Consul as Key-Values.
 This enables the Spring Consul Config to reload the service addresses in it's environment in case a change occurs
 in any of the keys.
 The following command is passed to the registrator container to run
 `-internal consulkv://consul:8500/config/store/service` which follows the following format: 
 `-internal consulkv://<consul_server>:<consul_port>/<app_config_path>/<prefix>`
 * the -internal argument means that the exposed ports of the service containers will be used instead of published ports.
 * the app_config_path is set to the path being used to store configuration for the store app
 * the prefix is set to service to differentiate the service locations from the rest of the application configuration.

The docker containers are passed the SERVICE_ID label, e.g. "SERVICE_ID=mongo" for the mongo container. This overrides
 the default name of key which stores the value of the service location, which is otherwise follows a cumbersome
 `<host>:<service_name>:<port>` format.
 This service location key can then be accessed from the spring environment as `"${service.mongo.mongo}"` which is
 actually following the format `"${<prefix>.<consul_service_key>.<SERVICE_ID>}"`

