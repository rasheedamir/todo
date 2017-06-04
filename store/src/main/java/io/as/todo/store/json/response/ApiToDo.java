package io.as.todo.store.json.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@ApiModel
@Value
@Builder(builderClassName = "Builder", builderMethodName = "newBuilder", toBuilder = true)
@JsonDeserialize(builder = ApiToDo.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiToDo
{
    @ApiModelProperty(position = 1, required = true, value = "The unique id of the todo")
    private final String id;
    @ApiModelProperty(position = 2, required = true, value = "Title of the todo")
    private final String title;
    @ApiModelProperty(position = 3, required = true, value = "True if todo is finished")
    private final boolean finished;
    @ApiModelProperty(position = 4, required = true, value = "Datetime when this todo was created")
    private final Date createdAt;


}
