package io.as.todo.search.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "todo")
public class ToDoProperties
{
    private final Kafka kafka = new Kafka();

    @Data
    public static class Kafka
    {
        private String bootstrapServers;
        private String todoTopic;
        private final Consumer consumer = new Consumer();

        @Data
        public static class Consumer
        {
            private String groupId;
        }
    }
}
