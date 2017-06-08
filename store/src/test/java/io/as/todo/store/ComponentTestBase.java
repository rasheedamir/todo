package io.as.todo.store;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //  tells JUnit to run using Spring’s testing support. SpringRunner is the new name for SpringJUnit4ClassRunner, it’s just a bit easier on the eye.
@SpringBootTest(classes = ToDoStoreApp.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, value = { "spring.cloud.consul.enabled=false", "spring.cloud.discovery.enabled=false"}) // is saying “bootstrap with Spring Boot’s support” (e.g. load application.properties and give me all the Spring Boot goodness)
@ActiveProfiles(AppConstants.SPRING_PROFILE_TEST) //this forces application-test.yml to be loaded
public abstract class ComponentTestBase
{
}
