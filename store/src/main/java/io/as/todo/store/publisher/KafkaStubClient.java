package io.as.todo.store.publisher;

import io.as.todo.core.domain.EntityEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class KafkaStubClient implements KafkaClient
{
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStubClient.class);

    @Override
    public SendResult<String, EntityEvent> send(String topic, String key, EntityEvent data)
    {
        return null;
    }
}
