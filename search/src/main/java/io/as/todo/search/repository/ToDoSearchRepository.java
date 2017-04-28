package io.as.todo.search.repository;

import io.as.todo.search.domain.ToDoEntity4Search;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ToDoSearchRepository extends ElasticsearchRepository<ToDoEntity4Search, String>
{

}
