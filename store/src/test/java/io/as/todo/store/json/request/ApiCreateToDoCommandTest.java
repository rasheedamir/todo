package io.as.todo.store.json.request;

import io.as.todo.store.UnitTest;
import io.as.todo.store.json.ApiValidationException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

@Category({UnitTest.class})
public class ApiCreateToDoCommandTest extends BaseCommandTest
{
    @Test
    public void verify_equals()
    {
        EqualsVerifier.forClass(ApiCreateToDoCommand.class).verify();
    }

    @Test(expected = ApiValidationException.class)
    public void title_is_mandatory()
    {
        ApiCreateToDoCommand.newBuilder().build();
    }

    @Test
    public void deserialize_should_set_title() throws Exception
    {
        String title = "My first todo";
        String json = "{\n" +
            "    \"title\": \"" + title + "\"\n" +
            "}";
        ApiCreateToDoCommand command = deserialize(json, ApiCreateToDoCommand.class);
        assertEquals(title, command.getTitle());
    }
}
