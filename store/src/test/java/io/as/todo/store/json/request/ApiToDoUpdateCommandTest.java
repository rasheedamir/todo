package io.as.todo.store.json.request;

import io.as.todo.store.json.ApiValidationException;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiToDoUpdateCommandTest extends BaseCommandTest
{
    @Test(expected = ApiValidationException.class)
    public void title_should_not_be_null()
    {
        ApiToDoUpdateCommand.newBuilder().title(null).build();
    }

    @Test
    public void verify_equals()
    {
        EqualsVerifier.forClass(ApiToDoUpdateCommand.class).verify();
    }

    @Test
    public void deserialize_should_set_isTitleSet() throws Exception
    {
        String json = "{\n" +
            "    \"title\": \"My first todo\"\n" +
            "}";
        ApiToDoUpdateCommand request = deserialize(json, ApiToDoUpdateCommand.class);
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
        ApiToDoUpdateCommand request = deserialize(json, ApiToDoUpdateCommand.class);
        assertThat(request.isTitleUpdated()).isEqualTo(true);
        assertThat(request.isFinishedUpdated()).isEqualTo(true);
    }

}
