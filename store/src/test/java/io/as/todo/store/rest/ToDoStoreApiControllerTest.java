package io.as.todo.store.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.as.todo.core.domain.ToDo;
import io.as.todo.store.NoConsul;
import io.as.todo.store.RestPath;
import io.as.todo.store.TestProfile;
import io.as.todo.store.UnitTest;
import io.as.todo.store.service.ToDoDispatcher;
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
        String id = "1";
        String title = "Title 1";
        boolean finished = false;
        Date date = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        ToDo expectedTodo = ToDo.newBuilder().id(id).title(title).finished(finished).createdAt(date).build();
        given(this.toDoDispatcher.dispatch(any(ToDo.class))).willReturn(expectedTodo);

        ApiCreateToDoCommand command = ApiCreateToDoCommand.newBuilder().title(title).build();
        ApiToDo expectedApiToDo = ApiToDo.newBuilder().id(id).title(title).finished(finished).createdAt(date).build();

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

        ApiToDo actualApiToDo = objectMapper.readValue(response.getResponse().getContentAsByteArray(), ApiToDo.class);
        assertThat(actualApiToDo).isEqualTo(expectedApiToDo);
    }

    @Test
    public void should_fail_to_create_todo_when_title_is_null() throws Exception
    {
        // GIVEN:

        // WHEN: & THEN:
        ApiCreateToDoCommand command = ApiCreateToDoCommand.newBuilder().title(null).build();

        MvcResult response = this.mvc
            .perform(
                post(RestPath.API_VERSION_1_TODO)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsBytes(command))
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )
            .andExpect(status().isBadRequest())
            // .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn();
    }

    @Test
    public void should_fail_to_create_todo_when_title_is_empty() throws Exception
    {
        // GIVEN:

        // WHEN: & THEN:
        ApiCreateToDoCommand command = ApiCreateToDoCommand.newBuilder().title("").build();

        MvcResult response = this.mvc
            .perform(
                post(RestPath.API_VERSION_1_TODO)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(objectMapper.writeValueAsBytes(command))
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )
            .andExpect(status().isBadRequest())
            // .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn();
    }
}
