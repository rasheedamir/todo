package io.as.todo.store.mapper;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.json.response.ApiToDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ToDoFactory.class)
public abstract class ToDoMapper
{
    public static ToDoMapper INSTANCE = Mappers.getMapper(ToDoMapper.class);

    public ApiToDo mapToDoToApiToDo(ToDo todo)
    {
        return mapToDoToApiToDoBuilder(todo).build();
    }

    abstract ApiToDo.Builder mapToDoToApiToDoBuilder(ToDo toDo);
}
