Components Used
===============

- [Docker](https://www.docker.com/what-docker)
- [Registrator](https://github.com/gliderlabs/registrator) - Service Registrar
- [Consul](https://www.consul.io/intro/) - Service Registry
- [Consul-Template](https://github.com/hashicorp/consul-template) - Configuration file template tool

Application flow
================

1. Docker Compose stands up all the containers needed
2. Registrator listens to the Docker Event System and automatically registers new containers to the Consul service discovery engine
3. Consul Template, built into our Nginx container, queries Consul for available services and automatically generates an nginx.conf file, based on the available services.
4. Nginx then round-robins the load across all the available services that Consul says exist.
5. As Docker Compose scales the number of <X> services, Registrator automatically registers these new instances in Consul.
6. Consul Template detects the changes in Consul, regenerates an updated nginx.conf with the new service endpoints, and then reloads the nginx configuration for updated _upstream_ servers.

Pre-requisites
==============

- Docker
- Docker Compose
