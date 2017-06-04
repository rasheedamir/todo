package io.as.todo.store.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder(builderClassName = "Builder", builderMethodName = "newBuilder", toBuilder = true)
@JsonDeserialize(builder = ApiToDo.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiToDo
{
    private final String id;
    private final String title;
    private final boolean finished;
    private final Date createdAt;

    /**
     * de-serialization doesn't work without it!
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder
    {
    }
}
