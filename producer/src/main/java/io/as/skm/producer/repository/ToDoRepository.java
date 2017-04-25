package io.as.skm.producer.repository;

import io.as.skm.core.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String>
{

}
