package io.as.todo.store.service;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.ComponentTest;
import io.as.todo.store.ComponentTestBase;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

@Category(ComponentTest.class)
public class ToDoDispatcherComponentTest extends ComponentTestBase
{
    // run embedded mongodb
    // run embedded kafka
    @Autowired
    private ToDoDispatcher toDoDispatcher;

    @Test
    public void should_dispatch_todo()
    {
        ToDo toDo = ToDo.newBuilder().title("Quang ToDo").build();
        toDoDispatcher.dispatch(toDo);
    }
}
