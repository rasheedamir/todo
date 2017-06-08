package io.as.todo.search.rest;

import io.as.todo.search.RestPath;
import io.as.todo.search.domain.ToDoPropName;
import io.as.todo.search.json.ApiToDo4Search;
import io.as.todo.search.service.ToDoQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoSearchApiController implements ToDoSearchApi
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoSearchApiController.class);

    private final ToDoQueryService toDoQueryService;

    @Autowired
    public ToDoSearchApiController(ToDoQueryService toDoQueryService)
    {
        this.toDoQueryService = toDoQueryService;
    }

    @Override
    @RequestMapping(value = RestPath.API_VERSION_1_TODO_SEARCH,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ApiToDo4Search>> searchToDos(
            @RequestParam(name = RequestParams.QUERY, required = false) String query,
            @RequestParam(name = RequestParams.INCLUDE_FIELDS, required = false) String includeFields[],
            @RequestParam(name = RequestParams.EXCLUDE_FIELDS, required = false) String excludeFields[],
            @PageableDefault(size = 5, sort = {ToDoPropName.TITLE}, direction = Sort.Direction.DESC) Pageable pageable)
    {
        LOGGER.info("Request to search todos");

        // TODO implement this!
        Page<ApiToDo4Search> page = null;
        HttpHeaders headers = null;

        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}
