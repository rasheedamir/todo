package io.as.todo.store.service;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.ComponentTest;
import io.as.todo.store.ComponentTestBase;
import io.as.todo.store.repository.ToDoStoreRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@Category(ComponentTest.class)
public class ToDoDispatcherComponentTest extends ComponentTestBase
{
    @Autowired
    private ToDoDispatcher toDoDispatcher;

    @Autowired
    private ToDoStoreRepository toDoStoreRepository;

    @Test
    public void should_dispatch_todo()
    {
        // given:
        ToDo expectedToDo = ToDo.newBuilder().title("Quang ToDo").build();

        // when:
        toDoDispatcher.dispatch(expectedToDo);

        // then:
        ToDo actualToDo = toDoStoreRepository.findOne(expectedToDo.getId());
        assertThat(actualToDo).isEqualTo(expectedToDo);
    }
}
