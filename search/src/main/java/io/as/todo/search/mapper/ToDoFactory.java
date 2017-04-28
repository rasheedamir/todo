package io.as.todo.search.mapper;

import io.as.todo.core.ToDo;
import io.as.todo.search.domain.ToDoEntity4Search;
import org.mapstruct.Mapper;

@Mapper
public abstract class ToDoFactory
{
    public ToDoEntity4Search.Builder createToDoEntity4Search()
    {
        return ToDoEntity4Search.newBuilder();
    }

    public ToDo.Builder createToDo()
    {
        return ToDo.newBuilder();
    }
}
