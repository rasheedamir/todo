package io.as.todo.store.json.response;

import io.as.todo.store.AppConstants;
import io.as.todo.store.UnitTest;
import org.junit.Ignore;
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
@ActiveProfiles(AppConstants.SPRING_PROFILE_TEST) //this forces application-test.yml to be loaded
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

        // Assert against a `.json` file in the same package as the test
        // assertThat(this.json.write(details)).isEqualToJson("expected.json");
    }

    @Ignore("Need to be fixed!")
    @Test
    public void should_deserialize_json()
    {

    }

    /*
	@Test
	public void serializeJson() throws Exception
	{
		VehicleDetails details = new VehicleDetails("Honda", "Civic");
		assertThat(this.json.write(details)).isEqualTo("vehicledetails.json");
		assertThat(this.json.write(details)).isEqualToJson("vehicledetails.json");
		assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
		assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make").isEqualTo("Honda");
	}

	@Test
	public void deserializeJson() throws Exception
	{
		String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
		assertThat(this.json.parse(content)).isEqualTo(new VehicleDetails("Ford", "Focus"));
		assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
	}
     */
}
