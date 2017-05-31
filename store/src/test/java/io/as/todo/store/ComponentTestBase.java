package io.as.todo.store;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ToDoStoreApp.class)
@ActiveProfiles(AppConstants.SPRING_PROFILE_TEST) //this forces application-test.yml to be loaded
public abstract class ComponentTestBase
{
}
