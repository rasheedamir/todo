package io.as.todo.store.json.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.as.todo.store.UnitTest;
import org.junit.experimental.categories.Category;

import java.io.IOException;

@Category({UnitTest.class})
public class BaseCommandTest
{
    public <T> T deserialize(String value, Class<T> clazz) throws IOException
    {
        // .registerModule(new JavaTimeModule())
        return new ObjectMapper().readValue(value, clazz);
    }
}
