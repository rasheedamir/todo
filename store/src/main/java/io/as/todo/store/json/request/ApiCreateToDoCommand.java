package io.as.todo.store.json.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.as.todo.store.json.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@ApiModel
@Value
@JsonDeserialize(builder = ApiCreateToDoCommand.Builder.class)
public final class ApiCreateToDoCommand implements Command
{
    @NotNull
    @NotEmpty
    @ApiModelProperty(position = 1, required = true, value = "Title of the todo")
    private final String title;

    private ApiCreateToDoCommand(String title)
    {
        this.title = title;
    }

    public static ApiCreateToDoCommand.Builder newBuilder()
    {
        return new ApiCreateToDoCommand.Builder();
    }

    public ApiCreateToDoCommand.Builder toBuilder()
    {
        return (new ApiCreateToDoCommand.Builder()).title(this.title);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder
    {
        private String title;

        private Builder()
        {
        }

        @JsonSetter
        public ApiCreateToDoCommand.Builder title(String title)
        {
            this.title = title;
            return this;
        }

        public ApiCreateToDoCommand build()
        {
            ApiCreateToDoCommand command = new ApiCreateToDoCommand(this.title);
            command.validate();
            return command;
        }

        public String toString()
        {
            return "ApiCreateToDoCommand.Builder(title=" + this.title + ")";
        }
    }

}
