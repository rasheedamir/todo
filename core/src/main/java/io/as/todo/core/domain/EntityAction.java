package io.as.todo.core.domain;

/**
 * An action that have been triggered for an entity.
 */
public enum EntityAction
{
    /**
     * The entity was either updated or inserted.
     */
    UPSERT,
    /**
     * The entity was removed
     */
    REMOVE
}
