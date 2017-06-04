package io.as.todo.store.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.as.todo.core.domain.ToDo;
import io.as.todo.store.NoConsul;
import io.as.todo.store.RestPath;
import io.as.todo.store.TestProfile;
import io.as.todo.store.UnitTest;
import io.as.todo.store.json.request.ApiCreateToDoCommand;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test focuses <strong>only</strong> on Spring MVC components.
 *
 * Mock the dependencies.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ToDoStoreApiController.class)
@NoConsul
@TestProfile
@Category({UnitTest.class}) // UNIT - SOLITARY
public class ToDoStoreApiControllerTest
{
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ToDoDispatcher toDoDispatcher;

    @Test
    public void should_create_todo() throws Exception
    {
        // GIVEN:
        String title = "Title 1";
        Date date = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));

        ToDo expectedTodo = ToDo.newBuilder().id("1").title(title).finished(false).createdAt(date).build();
        given(this.toDoDispatcher.dispatch(any(ToDo.class))).willReturn(expectedTodo);

        ApiToDo apiToDo = ToDoMapper.INSTANCE.mapToDoToApiToDo(expectedTodo);

        ApiCreateToDoCommand command = ApiCreateToDoCommand.newBuilder().title(title).build();

        // WHEN: & THEN:
        MvcResult response = this.mvc
            .perform(
                post(RestPath.API_VERSION_1_TODO)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(command))
                .accept(MediaType.APPLICATION_JSON_UTF8)
            )
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn();

        ToDo actualToDo = objectMapper.readValue(response.getResponse().getContentAsByteArray(), ToDo.class);
        assertThat(actualToDo).isEqualTo(expectedTodo);
    }

    @Ignore("Need to be fixed!")
    @Test
    public void should_fail_to_create_todo()
    {

    }
}
