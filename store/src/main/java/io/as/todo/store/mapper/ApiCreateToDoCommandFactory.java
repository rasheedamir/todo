package io.as.todo.store.mapper;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.json.request.ApiCreateToDoCommand;
import org.mapstruct.Mapper;

@Mapper
public class ApiCreateToDoCommandFactory
{
    public ToDo.Builder createPatient()
    {
        return ToDo.newBuilder();
    }

    public ApiCreateToDoCommand.Builder createApiCreateToDoCommand()
    {
        return ApiCreateToDoCommand.newBuilder();
    }
}
