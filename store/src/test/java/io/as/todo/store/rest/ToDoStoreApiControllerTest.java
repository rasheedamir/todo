package io.as.todo.store.rest;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.AppConstants;
import io.as.todo.store.RestPath;
import io.as.todo.store.UnitTest;
import io.as.todo.store.json.response.ApiToDo;
import io.as.todo.store.mapper.ToDoMapper;
import io.as.todo.store.service.ToDoDispatcher;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test focuses <strong>only</strong> on Spring MVC components.
 *
 * Mock the dependencies.
 *
 * Unit Test or Integration Test?
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ToDoStoreApiController.class)
@ActiveProfiles(AppConstants.SPRING_PROFILE_TEST) //this forces application-test.yml to be loaded
@Category({UnitTest.class}) // UNIT - SOLITARY
public class ToDoStoreApiControllerTest
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ToDoDispatcher toDoDispatcher;

    @Ignore("Need to be fixed!")
    @Test
    public void should_create_todo() throws Exception
    {
        // GIVEN:
        ToDo todo = ToDo.newBuilder().id("1").title("Title 1").finished(true).createdAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))).build();
        given(this.toDoDispatcher.dispatch(todo)).willReturn(todo);

        ApiToDo apiToDo = ToDoMapper.INSTANCE.mapToDoToApiToDo(todo);

        // WHEN: & THEN:
        this.mvc
            .perform(post(RestPath.TODO)
                // TODO add body
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Honda Civic")); // TODO fix this
    }

    @Ignore("Need to be fixed!")
    @Test
    public void should_fail_to_create_todo()
    {

    }
}
