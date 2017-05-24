FROM        stakater/tomcat8-on-alpine:8.5.11

WORKDIR     /tomcat
RUN         rm -Rf webapps/*
ADD         *.war webapps/ROOT.war
