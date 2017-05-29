package io.as.todo.store.json.request;

import io.as.todo.store.UnitTest;
import io.as.todo.store.json.ApiValidationException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.assertThat;

@Category({UnitTest.class})
public class ApiUpdateToDoCommandTest extends BaseCommandTest
{
    @Test(expected = ApiValidationException.class)
    public void title_should_not_be_null()
    {
        ApiUpdateToDoCommand.newBuilder().title(null).build();
    }

    @Test
    public void verify_equals()
    {
        EqualsVerifier.forClass(ApiUpdateToDoCommand.class).verify();
    }

    @Test
    public void deserialize_should_set_isTitleSet() throws Exception
    {
        String json = "{\n" +
            "    \"title\": \"My first todo\"\n" +
            "}";
        ApiUpdateToDoCommand request = deserialize(json, ApiUpdateToDoCommand.class);
        assertThat(request.isTitleUpdated()).isEqualTo(true);
        assertThat(request.isFinishedUpdated()).isEqualTo(false);
    }

    @Test
    public void deserialize_should_set_isFinishedSet() throws Exception
    {
        String json = "{\n" +
            "    \"title\": \"My first todo\",\n" +
            "    \"isFinished\": \"true\"\n" +
            "}";
        ApiUpdateToDoCommand request = deserialize(json, ApiUpdateToDoCommand.class);
        assertThat(request.isTitleUpdated()).isEqualTo(true);
        assertThat(request.isFinishedUpdated()).isEqualTo(true);
    }

}
