package io.as.todo.producer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "todo")
public class ToDoProperties
{
    private final Kafka kafka = new Kafka();

    @Getter
    @Setter
    public static class Kafka
    {
        private String bootstrap;
        private String todoTopic;
    }
}
