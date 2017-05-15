package io.as.todo.store.rest;

import io.as.todo.core.domain.ToDo;
import io.as.todo.store.json.request.ApiCreateToDoCommand;
import io.as.todo.store.mapper.ApiCreateToDoCommandMapper;
import io.as.todo.store.service.ToDoDispatcher;
import io.as.todo.store.RestPath;
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
    public ResponseEntity<ToDo> create(@Valid @RequestBody ApiCreateToDoCommand command)
    {
        ToDo dispatchedToDo = toDoDispatcher.dispatch(ApiCreateToDoCommandMapper.INSTANCE.mapApiCreateToDoCommandToToDo(command));
        return ResponseEntity.created(createUri(dispatchedToDo))
                .body(dispatchedToDo);
    }

    // TODO - add update
    // TODO - add delete

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
