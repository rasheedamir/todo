package io.as.todo.store.publisher;

import io.as.todo.core.domain.EntityEvent;
import io.as.todo.store.config.ToDoProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class KafkaLiveClient implements KafkaClient
{
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaLiveClient.class);

    private final KafkaTemplate<String, EntityEvent> toDosKafkaTemplate;
    private final ToDoProperties toDoProperties;

    @Override
    public SendResult<String, EntityEvent> send(String topic, String key, EntityEvent data)
    {
        try
        {
            return toDosKafkaTemplate.send(toDoProperties.getKafka().getTodoTopic(), data.getEntityId(), data).get();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
