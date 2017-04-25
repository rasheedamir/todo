package io.as.skm.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Value;

/**
 * Class that encapsulates the event for an entity.
 *
 */
@Value /* to generate equals, hashCode, getters & toString ( immutable ) */
@JsonDeserialize(builder = EntityEvent.Builder.class)
public class EntityEvent
{
    private final String entityId;
    private final ToDo entity;
    private final EntityAction entityAction;

    @lombok.Builder(builderClassName = "Builder", builderMethodName = "newBuilder", toBuilder = true)
    private EntityEvent(String entityId, ToDo entity, EntityAction entityAction)
    {
        this.entityId = entityId;
        this.entity = entity;
        this.entityAction = entityAction;

        if (entityAction == EntityAction.UPSERT && entity == null)
        {
            throw new IllegalArgumentException("Entity is required for UPSERT");
        }
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder
    {
    }
}
