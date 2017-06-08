package io.as.todo.store;

/**
 * Persistence integration tests provide assurances that the schema assumed by the code matches that available in the
 * data store.
 *
 * In the case that an ORM is in use, these tests also give confidence that any mappings configured in the tool are
 * compatible with the result sets coming back.
 *
 * Modern ORMs are very sophisticated in terms of caching and only flushing when necessary. It is important to structure
 * tests such that transactions close in between preconditions, actions and assertions to be sure data makes a full
 * round trip
 *
 * Since most data stores exist across a network partition, they are also subject to timeouts and network failures.
 * Integration tests should attempt to verify that the integration modules handle these failures gracefully.
 */
public interface PersistenceIntegrationTest
{

}
