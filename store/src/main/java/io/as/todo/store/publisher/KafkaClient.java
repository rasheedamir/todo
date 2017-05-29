package io.as.todo.store.publisher;

import io.as.todo.core.domain.EntityEvent;
import org.springframework.kafka.support.SendResult;

public interface KafkaClient
{
    SendResult<String, EntityEvent> send(String topic, String key, EntityEvent data);
}
