package io.as.todo.producer.repository;

import io.as.todo.core.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String>
{

}
