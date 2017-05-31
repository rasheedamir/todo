package io.as.todo.store.config;

import io.as.todo.core.domain.EntityEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@SuppressWarnings("ALL")
public class KafkaConsumerConfig
{
    private final ToDoProperties toDoProperties;

    @Autowired
    public KafkaConsumerConfig(ToDoProperties toDoProperties)
    {
        this.toDoProperties = toDoProperties;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EntityEvent> kafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, EntityEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(1);
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    // consumerfactory needs deserializers
    @Bean
    public ConsumerFactory<String, EntityEvent> consumerFactory()
    {
        // consumer needs two things:
        // 1. properties
        // 2. deserializers
        return new DefaultKafkaConsumerFactory<>(consumerProps(),
                stringKeyDeserializer(),
                entityEventJsonValueDeserializer()
        );
    }

    @Bean
    public Map<String, Object> consumerProps()
    {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, toDoProperties.getKafka().getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, toDoProperties.getKafka().getConsumer().getGroupId());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        return props;
    }

    @Bean
    public Deserializer stringKeyDeserializer()
    {
        return new StringDeserializer();
    }

    @Bean
    public Deserializer entityEventJsonValueDeserializer()
    {
        return new JsonDeserializer(EntityEvent.class);
    }
}
