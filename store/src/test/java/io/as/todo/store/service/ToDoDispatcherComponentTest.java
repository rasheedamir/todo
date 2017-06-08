package io.as.todo.store.service;

import io.as.todo.core.domain.EntityEvent;
import io.as.todo.core.domain.ToDo;
import io.as.todo.store.ComponentTest;
import io.as.todo.store.ComponentTestBase;
import io.as.todo.store.repository.ToDoStoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * this test shld be run through the suite
 *
 */
@Category(ComponentTest.class) // TODO this is wrong as component test should run through whole "micro-service"
public class ToDoDispatcherComponentTest extends ComponentTestBase
{
    @Autowired
    private ToDoDispatcher toDoDispatcher;

    @Autowired
    private ToDoStoreRepository toDoStoreRepository;

    @Autowired
    private Receiver receiver;

    @Test
    public void should_dispatch_todo() throws Exception
    {
        // given:
        ToDo expectedToDo = ToDo.newBuilder().title("Quang ToDo").build();

        // when:
        toDoDispatcher.dispatch(expectedToDo);

        // then:
        // verify its saved in the database
        ToDo actualToDo = toDoStoreRepository.findOne(expectedToDo.getId());
        assertThat(actualToDo).isEqualTo(expectedToDo);
        // verify its sent on kafka
        receiver.getLatch().await(20000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0); // check that the message was received
    }
}

@Service
class Receiver
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch()
    {
        return latch;
    }

    @KafkaListener(topics = "${todo.kafka.todo-topic}")
    public void receive(EntityEvent message)
    {
        LOGGER.info("received message='{}'", message);
        latch.countDown();
    }
}
