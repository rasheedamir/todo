package io.as.todo.search.rest;

import io.as.todo.search.RestPath;
import io.as.todo.search.domain.ToDoPropName;
import io.as.todo.search.json.ApiToDo4Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ToDoSearchApi
{
    @RequestMapping(value = RestPath.API_VERSION_1_TODO_SEARCH,
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
     ResponseEntity<Page<ApiToDo4Search>> searchToDos(
        @RequestParam(name = RequestParams.QUERY, required = false) String query,
        @RequestParam(name = RequestParams.INCLUDE_FIELDS, required = false) String includeFields[],
        @RequestParam(name = RequestParams.EXCLUDE_FIELDS, required = false) String excludeFields[],
        @PageableDefault(size = 5, sort = {ToDoPropName.TITLE}, direction = Sort.Direction.DESC) Pageable pageable);
}
