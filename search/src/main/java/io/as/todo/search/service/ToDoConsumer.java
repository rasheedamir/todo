package io.as.todo.search.service;

import io.as.todo.core.EntityEvent;
import io.as.todo.search.domain.ToDoEntity4Search;
import io.as.todo.search.mapper.ToDoMapper;
import io.as.todo.search.repository.ToDoSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ToDoConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoConsumer.class);

    private final ToDoSearchRepository toDoSearchRepository;

    @Autowired
    public ToDoConsumer(ToDoSearchRepository toDoSearchRepository)
    {
        this.toDoSearchRepository = toDoSearchRepository;
    }

    @KafkaListener(topics = "todo") // TODO - read from props file!
    public void onReceiving(EntityEvent entityEvent,
                            @Header(KafkaHeaders.OFFSET) Integer offset,
                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)
    {
        LOGGER.info("Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
                topic, partition, offset, entityEvent);

        ToDoEntity4Search toDoEntity4Search = toDoSearchRepository.save(ToDoMapper.INSTANCE.mapToDoToToDoEntity4Search(entityEvent.getEntity()));

        LOGGER.info("Saved = {}", toDoEntity4Search);
    }
}
