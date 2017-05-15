package io.as.todo.store.mapper;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.json.request.ApiCreateToDoCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiCreateToDoCommandMapperTest
{
    @Test
    public void should_map_apiCreateToDoCommand_2_todo()
    {
        String title = "first todo";
        ApiCreateToDoCommand command = ApiCreateToDoCommand.newBuilder().title(title).build();
        ToDo toDo = ApiCreateToDoCommandMapper.INSTANCE.mapApiCreateToDoCommandToToDo(command);

        assertEquals(title, toDo.getTitle());
        assertEquals(false, toDo.isFinished());
    }
}
