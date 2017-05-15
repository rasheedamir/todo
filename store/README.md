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

