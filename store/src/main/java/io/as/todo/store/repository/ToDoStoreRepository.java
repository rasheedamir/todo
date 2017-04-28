package io.as.todo.store.repository;

import io.as.todo.core.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoStoreRepository extends MongoRepository<ToDo, String>
{

}
