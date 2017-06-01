package io.as.todo.store;

/**
 * Gateway integration tests allow any protocol level errors such as missing HTTP headers, incorrect SSL handling or
 * request/response body mismatches to be flushed out at the finest testing granularity possible.
 *
 * Any special case error handling should also be tested to ensure the service and protocol client employed respond
 * as expected in exceptional circumstances.
 *
 * At times it is difficult to trigger abnormal behaviours such as timeouts or slow responses from the external
 * component. In this case it can be beneficial to use a stub version of the external component as a test harness
 * which can be configured to fail in predetermined ways.
 *
 * State management can be difficult when testing against external components since the tests will rely on certain data
 * being available. One way to mitigate this problem is to agree on a fixed set of representative but harmless data
 * that is guaranteed to be available in every environment.
 */
public interface GatewayIntegrationTest
{
}
