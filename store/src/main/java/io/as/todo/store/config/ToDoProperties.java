package io.as.todo.store.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "todo")
public class ToDoProperties
{
    private final Kafka kafka = new Kafka();
    private final Swagger swagger = new Swagger();

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

    @Data
    public static class Swagger
    {
        private String title;
        private String description;
        private String version;
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
    }
}
