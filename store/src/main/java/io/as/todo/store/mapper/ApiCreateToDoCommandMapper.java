package io.as.todo.store.mapper;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.json.request.ApiCreateToDoCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ApiCreateToDoCommandFactory.class)
public abstract class ApiCreateToDoCommandMapper
{
    public static ApiCreateToDoCommandMapper INSTANCE = Mappers.getMapper(ApiCreateToDoCommandMapper.class);

    public ToDo mapApiCreateToDoCommandToToDo(ApiCreateToDoCommand apiCreateToDoCommand)
    {
        return mapApiCreateToDoCommandToToDoBuilder(apiCreateToDoCommand).build();
    }

    abstract ToDo.Builder mapApiCreateToDoCommandToToDoBuilder(ApiCreateToDoCommand apiCreateToDoCommand);
}
