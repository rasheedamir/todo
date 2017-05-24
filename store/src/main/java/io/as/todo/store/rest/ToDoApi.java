package io.as.todo.store.rest;

import io.as.todo.store.RestPath;
import io.as.todo.store.json.request.ApiCreateToDoCommand;
import io.as.todo.store.json.response.ApiToDo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "todo", description = "The todo API")
public interface ToDoApi
{
    @ApiOperation(value = "Add a new todo to the store", notes = "This adds a new todo to the store!", response = ApiToDo.class, tags={"todo"})
    @ApiResponses(value = {
        @ApiResponse(code = 405, message = "Invalid input", response = Void.class), // TODO - fix this!
        @ApiResponse(code = 500, message = "Internal server error", response = Void.class) }) // TODO - fix this!
    @RequestMapping(value = RestPath.TODO,
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiToDo> addToDo(@ApiParam(value = "ToDo object that needs to be added to the store" ,required=true ) @Valid @RequestBody ApiCreateToDoCommand command);

}
