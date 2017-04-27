package io.as.todo.consumer.service;

import io.as.todo.core.EntityEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ToDoConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoConsumer.class);

    @KafkaListener(topics = "workunits")
    public void onReceiving(EntityEvent entityEvent,
                            @Header(KafkaHeaders.OFFSET) Integer offset,
                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic)
    {
        LOGGER.info("Processing topic = {}, partition = {}, offset = {}, workUnit = {}",
                topic, partition, offset, entityEvent);

        // TODO - add more logic!
    }
}
