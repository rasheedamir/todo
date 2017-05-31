package io.as.todo.store.service;

import io.as.todo.core.domain.EntityAction;
import io.as.todo.core.domain.EntityEvent;
import io.as.todo.core.domain.ToDo;
import io.as.todo.store.config.ToDoProperties;
import io.as.todo.store.repository.ToDoStoreRepository;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

/**
 * Does two things:
 *
 * 1. saves in mongodb collection
 * 2. posts it to kafka
 */
@Service
public class ToDoDispatcher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoDispatcher.class);

    private final ToDoStoreRepository toDoStoreRepository;
    private final KafkaTemplate<String, EntityEvent> toDosKafkaTemplate;
    private final ToDoProperties toDoProperties;

    @Autowired
    public ToDoDispatcher(ToDoStoreRepository toDoStoreRepository, KafkaTemplate<String, EntityEvent> toDosKafkaTemplate,
                          ToDoProperties toDoProperties)
    {
        this.toDoStoreRepository = toDoStoreRepository;
        this.toDosKafkaTemplate = toDosKafkaTemplate;
        this.toDoProperties = toDoProperties;
    }

    public ToDo dispatch(ToDo toDo)
    {
        // save in database
        ToDo persistedToDo = toDoStoreRepository.save(toDo);
        LOGGER.info("persisted in database {}", toDo);

        // post it
        EntityEvent entityEvent = createEvent(persistedToDo);
        SendResult<String, EntityEvent> sendResult;
        try
        {
            sendResult = toDosKafkaTemplate.send(toDoProperties.getKafka().getTodoTopic(), entityEvent.getEntityId(), entityEvent).get();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();
        LOGGER.info("topic = {}, partition = {}, offset = {}, todo = {}", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), entityEvent);
        return persistedToDo;
    }

    private static EntityEvent createEvent(ToDo toDo)
    {
        return EntityEvent
                .newBuilder()
                .entity(toDo)
                .entityId(toDo.getId())
                .entityAction(EntityAction.UPSERT)
                .build();
    }
}
