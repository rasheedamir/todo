package io.as.todo.store.json.response;

import io.as.todo.store.AppConstants;
import io.as.todo.store.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
//@IntegrationTest({"server.port=0", "spring.cloud.bus.enabled=false", "spring.cloud.discovery.enabled=false"})
@ActiveProfiles(AppConstants.SPRING_PROFILE_TEST) //this forces application-test.yml & bootstrap-test.yml to be loaded
@Category({UnitTest.class})
public class ApiToDoJsonTest
{
    @Autowired
    private JacksonTester<ApiToDo> json;

    @Test
    public void should_serialize_json() throws Exception
    {
        String id = "1";
        String title = "Title";
        boolean finished = true;
        ApiToDo apiToDo = ApiToDo.newBuilder().id(id).title(title).finished(finished).build();

        assertThat(this.json.write(apiToDo)).extractingJsonPathStringValue("@.title").isEqualTo(title);
        assertThat(this.json.write(apiToDo)).hasJsonPathStringValue("@.title");

        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(apiToDo)).isEqualTo("expected.json");
        assertThat(this.json.write(apiToDo)).isEqualToJson("expected.json");
    }

    @Test
    public void should_deserialize_json() throws Exception
    {
        ApiToDo apiToDo = ApiToDo.newBuilder().id("1").title("Title").finished(true).build();
        String content = "{\n" +
            "    \"id\": \"1\",\n" +
            "    \"title\": \"Title\",\n" +
            "    \"finished\": true\n" +
            "}";
        assertThat(this.json.parse(content)).isEqualTo(apiToDo);
        assertThat(this.json.parseObject(content).getTitle()).isEqualTo("Title");
    }
}
