package io.as.todo.search.service;

import io.as.todo.search.domain.ToDoEntity4Search;
import io.as.todo.search.repository.ToDoSearchRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.SourceFilter;
import org.springframework.stereotype.Service;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@Service
public class ToDoQueryService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoQueryService.class);

    private final ToDoSearchRepository toDoSearchRepository;

    @Autowired
    public ToDoQueryService(ToDoSearchRepository toDoSearchRepository)
    {
        this.toDoSearchRepository = toDoSearchRepository;
    }

    public Page<ToDoEntity4Search> search(String query, String[] includeFields, Pageable pageable)
    {
        LOGGER.info("Search todos query {} | responseFields {} | pageable {}", query, includeFields, pageable);

        SourceFilter sourceFilter = new FetchSourceFilter(includeFields, null);
        QueryBuilder builder = queryStringQuery(query);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withSourceFilter(sourceFilter)
                .withPageable(pageable)
                .build();

        Page<ToDoEntity4Search> page = toDoSearchRepository.search(searchQuery);

        return page;
    }
}
