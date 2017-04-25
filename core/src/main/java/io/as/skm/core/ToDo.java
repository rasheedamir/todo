package io.as.skm.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Value /* to generate equals, hashCode, getters & toString ( immutable ) */
@NoArgsConstructor(force = true, access = AccessLevel.PACKAGE) /* just for dehydration! as default ctor is used to create object & then fields are set using refection! */
@JsonDeserialize(builder = ToDo.Builder.class)
public class ToDo
{
    @Id
    private final String id;

    @NotNull
    @NotEmpty
    private final String title;

    private final boolean finished;
    private final Date createdAt;

    @lombok.Builder(builderClassName = "Builder", builderMethodName = "newBuilder", toBuilder = true)
    private ToDo(String id, @NonNull String title, boolean finished, Date createdAt)
    {
        this.id = id;
        this.title = title;
        this.finished = finished;
        this.createdAt = createdAt;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder
    {
    }
}