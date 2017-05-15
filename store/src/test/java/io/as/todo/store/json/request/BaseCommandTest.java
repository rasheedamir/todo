package io.as.todo.store.json.request;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BaseCommandTest
{
    public <T> T deserialize(String value, Class<T> clazz) throws IOException
    {
        // .registerModule(new JavaTimeModule())
        return new ObjectMapper().readValue(value, clazz);
    }
}
