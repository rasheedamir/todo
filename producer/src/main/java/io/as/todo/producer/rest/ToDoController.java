package io.as.todo.producer.rest;

import io.as.todo.core.ToDo;
import io.as.todo.producer.service.ToDoDispatcher;
import io.as.todo.producer.RestPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(RestPath.API_VERSION_1)
public class ToDoController
{
    private final ToDoDispatcher toDoDispatcher;

    @Autowired
    public ToDoController(ToDoDispatcher toDoDispatcher)
    {
        this.toDoDispatcher = toDoDispatcher;
    }

    @RequestMapping(value = RestPath.TODO,
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> create(@Valid @RequestBody ToDo toDo)
    {
        ToDo dispatchedToDo = toDoDispatcher.dispatch(toDo);
        return ResponseEntity.created(createUri(dispatchedToDo))
                .body(dispatchedToDo);
    }

    private static URI createUri(ToDo toDo)
    {
        try
        {
            return new URI("todo" + "/" + toDo.getId());
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }
}
