package io.as.todo.store;

import io.as.todo.store.service.ToDoDispatcherComponentTest;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.test.rule.KafkaEmbedded;

/**
 * This allows us to only start the embedded broker once for all test cases!
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ToDoDispatcherComponentTest.class})
@Category(ComponentTest.class)
public class ComponentTestSuite
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentTestSuite.class);

    public static final String SENDER_TOPIC = "sender.t";
    public static final String RECEIVER_TOPIC = "receiver.t";

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, SENDER_TOPIC, RECEIVER_TOPIC);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        String kafkaBootstrapServers = embeddedKafka.getBrokersAsString();

        LOGGER.debug("kafkaServers='{}'", kafkaBootstrapServers);

        // override the property in application.properties
        System.setProperty("todo.kafka.bootstrap-servers", kafkaBootstrapServers);
        System.setProperty("todo.kafka.todo-topic", SENDER_TOPIC);
    }
}
