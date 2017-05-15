/**
 *
 * Show all logging events for one Web request only
 *
 * If you add trace=on on a request then it will log at all levels (TRACE, DEBUG, INFO, WARN, ERROR)
 *
 * Credits: https://moelholm.com/2016/08/24/spring-boot-show-all-logging-events-for-one-web-request-only/
 *
 * Adding @Order(Ordered.HIGHEST_PRECEDENCE) makes it the first filter to accept the request; so, that we can log stuff on every kind of request.
 *
 * 2017-05-10 14:09:31.212  INFO 41863 --- [ost-startStop-1] o.s.web.context.ContextLoader            [ebApplicationContext] Root WebApplicationContext: initialization completed in 3973 ms
 * 2017-05-10 14:09:33.560  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'threadLoggingFilterBean' to: [/*]
 * 2017-05-10 14:09:33.560  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'metricsFilter' to: [/*]
 * 2017-05-10 14:09:33.561  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'characterEncodingFilter' to: [/*]
 * 2017-05-10 14:09:33.561  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'unauthorizedLogbookFilter' to: [/*]
 * 2017-05-10 14:09:33.562  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
 * 2017-05-10 14:09:33.562  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'httpPutFormContentFilter' to: [/*]
 * 2017-05-10 14:09:33.563  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'requestContextFilter' to: [/*]
 * 2017-05-10 14:09:33.565  INFO 41863 --- [ost-startStop-1] .s.DelegatingFilterProxyRegistrationBean [configure           ] Mapping filter: 'springSecurityFilterChain' to: [/*]
 * 2017-05-10 14:09:33.566  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'webRequestLoggingFilter' to: [/*]
 * 2017-05-10 14:09:33.566  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'authorizedLogbookFilter' to: [/*]
 * 2017-05-10 14:09:33.567  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   [configure           ] Mapping filter: 'applicationContextIdFilter' to: [/*]
 * 2017-05-10 14:09:33.567  INFO 41863 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  [onStartup           ] Mapping servlet: 'dispatcherServlet' to [/]
 *
 */
package io.as.todo.search.logging;
