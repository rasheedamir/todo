package io.as.todo.store.json.request;

import io.as.todo.store.json.ApiValidationException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApiToDoCreateCommandTest extends BaseCommandTest
{
    @Test
    public void verify_equals()
    {
        EqualsVerifier.forClass(ApiToDoCreateCommand.class).verify();
    }

    @Test(expected = ApiValidationException.class)
    public void title_is_mandatory()
    {
        ApiToDoCreateCommand.newBuilder().build();
    }

    @Test
    public void deserialize_should_set_title() throws Exception
    {
        String title = "My first todo";
        String json = "{\n" +
            "    \"title\": \"" + title + "\"\n" +
            "}";
        ApiToDoCreateCommand command = deserialize(json, ApiToDoCreateCommand.class);
        assertEquals(title, command.getTitle());
    }
}
