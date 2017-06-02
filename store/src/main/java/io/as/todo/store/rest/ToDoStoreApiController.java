package io.as.todo.store.rest;

import io.as.todo.store.RestPath;
import io.as.todo.store.json.request.ApiCreateToDoCommand;
import io.as.todo.store.json.response.ApiToDo;
import io.as.todo.store.mapper.ApiCreateToDoCommandMapper;
import io.as.todo.store.mapper.ToDoMapper;
import io.as.todo.store.service.ToDoDispatcher;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(RestPath.API_VERSION_1)
public class ToDoStoreApiController implements ToDoStoreApi
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoStoreApiController.class);

    private final ToDoDispatcher toDoDispatcher;

    @Autowired
    public ToDoStoreApiController(ToDoDispatcher toDoDispatcher)
    {
        this.toDoDispatcher = toDoDispatcher;
    }

    @Override
    public ResponseEntity<ApiToDo> addToDo(@ApiParam(value = "ToDo object that needs to be added to the store" ,required=true ) @Valid @RequestBody ApiCreateToDoCommand command)
    {
        ApiToDo dispatchedToDo = ToDoMapper.INSTANCE.mapToDoToApiToDo(toDoDispatcher.dispatch(ApiCreateToDoCommandMapper.INSTANCE.mapApiCreateToDoCommandToToDo(command)));
        return ResponseEntity.created(createUri(dispatchedToDo)).body(dispatchedToDo);
    }

    // TODO - add update
    // TODO - add delete

    private static URI createUri(ApiToDo toDo)
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
