package io.as.todo.store.json.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.as.todo.store.json.Command;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * to support both partial and full updates
 */
@Value
@JsonDeserialize(builder = ApiToDoUpdateCommand.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ApiToDoUpdateCommand implements Command
{
    // TODO - they should be only applied if user changed them; otherwise must be ignored
    @NotNull
    @NotEmpty
    private final String title;
    private final boolean isTitleUpdated;

    private final boolean isFinished;
    private final boolean isFinishedUpdated;

    private ApiToDoUpdateCommand(String title, boolean isTitleUpdated, boolean isFinished, boolean isFinishedUpdated)
    {
        this.title = title;
        this.isTitleUpdated = isTitleUpdated;
        this.isFinished = isFinished;
        this.isFinishedUpdated = isFinishedUpdated;
    }

    public static ApiToDoUpdateCommand.Builder newBuilder()
    {
        return new ApiToDoUpdateCommand.Builder();
    }

    public ApiToDoUpdateCommand.Builder toBuilder()
    {
        return (new ApiToDoUpdateCommand.Builder()).title(this.title).isFinished(this.isFinished);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder
    {
        private String title;
        private boolean isTitleUpdated;
        private boolean isFinished;
        private boolean isFinishedUpdated;

        private Builder()
        {
        }

        @JsonSetter
        public ApiToDoUpdateCommand.Builder title(String title)
        {
            this.title = title;
            isTitleUpdated = true;
            return this;
        }

        @JsonSetter
        public ApiToDoUpdateCommand.Builder isFinished(boolean isFinished)
        {
            this.isFinished = isFinished;
            isFinishedUpdated = true;
            return this;
        }

        public ApiToDoUpdateCommand build()
        {
            ApiToDoUpdateCommand command = new ApiToDoUpdateCommand(this.title, this.isTitleUpdated, this.isFinished, this.isFinishedUpdated);
            command.validate();
            return command;
        }

        public String toString()
        {
            return "ApiToDoUpdateCommand.Builder(title=" + this.title + ", isTitleUpdated=" + this.isTitleUpdated + ", isFinished=" + this.isFinished + ", isFinishedUpdated=" + this.isFinishedUpdated + ")";
        }

    }
}
