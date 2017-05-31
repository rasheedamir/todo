package io.as.todo.store.mapper;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.UnitTest;
import io.as.todo.store.json.response.ApiToDo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@Category({UnitTest.class})
public class ToDoMapperTest
{
    @Test
    public void should_map_todo_apiToDo()
    {
        LocalDateTime now = LocalDateTime.now();

        ToDo toDo = ToDo
            .newBuilder()
            .id("1")
            .title("first todo")
            .finished(true)
            .createdAt(Date.from(now.toInstant(ZoneOffset.UTC)))
            .build();

        ApiToDo apiToDo = ToDoMapper.INSTANCE.mapToDoToApiToDo(toDo);

        assertEquals(apiToDo.getId(), toDo.getId());
        assertEquals(apiToDo.getTitle(), toDo.getTitle());
        assertEquals(apiToDo.isFinished(), toDo.isFinished());
        assertEquals(apiToDo.getCreatedAt(), toDo.getCreatedAt());
    }
}
