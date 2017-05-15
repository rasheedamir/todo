package io.as.todo.store.json.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.as.todo.store.json.Command;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Value
@JsonDeserialize(builder = ApiToDoCreateCommand.Builder.class)
public final class ApiToDoCreateCommand implements Command
{
    @NotNull
    @NotEmpty
    private final String title;

    private ApiToDoCreateCommand(String title)
    {
        this.title = title;
    }

    public static ApiToDoCreateCommand.Builder newBuilder()
    {
        return new ApiToDoCreateCommand.Builder();
    }

    public ApiToDoCreateCommand.Builder toBuilder()
    {
        return (new ApiToDoCreateCommand.Builder()).title(this.title);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder
    {
        private String title;

        private Builder()
        {
        }

        @JsonSetter
        public ApiToDoCreateCommand.Builder title(String title)
        {
            this.title = title;
            return this;
        }

        public ApiToDoCreateCommand build()
        {
            ApiToDoCreateCommand command = new ApiToDoCreateCommand(this.title);
            command.validate();
            return command;
        }

        public String toString()
        {
            return "ApiToDoCreateCommand.Builder(title=" + this.title + ")";
        }
    }

}
