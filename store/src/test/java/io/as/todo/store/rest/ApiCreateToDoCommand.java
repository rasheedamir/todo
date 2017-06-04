package io.as.todo.store.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder", builderMethodName = "newBuilder", toBuilder = true)
@JsonDeserialize(builder = ApiCreateToDoCommand.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ApiCreateToDoCommand
{
    private final String title;
}
