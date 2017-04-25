package io.as.skm.producer.config;

import io.as.skm.core.EntityEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@SuppressWarnings("ALL")
public class KafkaProducerConfig
{
    private final KafkaProducerProperties kafkaProducerProperties;

    @Autowired
    public KafkaProducerConfig(KafkaProducerProperties kafkaProducerProperties)
    {
        this.kafkaProducerProperties = kafkaProducerProperties;
    }

    // factory needs a serializer
    @Bean
    public ProducerFactory<String, EntityEvent> producerFactory()
    {
        return new DefaultKafkaProducerFactory<>(producerConfigs(),
                stringKeySerializer(),
                entityEventSerializer());
    }

    @Bean
    public Map<String, Object> producerConfigs()
    {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrap());
        return props;
    }

    // template needs a factory
    @Bean
    public KafkaTemplate<String, EntityEvent> workUnitsKafkaTemplate()
    {
        KafkaTemplate<String, EntityEvent> kafkaTemplate =  new KafkaTemplate<>(producerFactory());
        return kafkaTemplate;
    }

    @Bean
    public Serializer stringKeySerializer()
    {
        return new StringSerializer();
    }

    @Bean
    public Serializer entityEventSerializer()
    {
        return new JsonSerializer();
    }
}
