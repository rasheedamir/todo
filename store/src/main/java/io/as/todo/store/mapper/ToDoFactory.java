package io.as.todo.store.mapper;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.json.response.ApiToDo;
import org.mapstruct.Mapper;

@Mapper
public abstract class ToDoFactory
{
    public ApiToDo.Builder createApiToDo()
    {
        return ApiToDo.newBuilder();
    }

    public ToDo.Builder createToDo()
    {
        return ToDo.newBuilder();
    }
}
