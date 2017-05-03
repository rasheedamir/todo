package io.as.todo.search.mapper;

import io.as.todo.core.domain.ToDo;
import io.as.todo.search.domain.ToDoEntity4Search;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ToDoFactory.class)
public abstract class ToDoMapper
{
    public static ToDoMapper INSTANCE = Mappers.getMapper(ToDoMapper.class);

    public ToDoEntity4Search mapToDoToToDoEntity4Search(ToDo todo)
    {
        return mapToDoToToDoEntity4SearchBuilder(todo).build();
    }

    abstract ToDoEntity4Search.Builder mapToDoToToDoEntity4SearchBuilder(ToDo toDo);
}
