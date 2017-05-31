package io.as.todo.store.json.response;

import io.as.todo.store.UnitTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category({UnitTest.class})
public class ApiToDoTest
{
    @Test
    public void verify_equals()
    {
        EqualsVerifier.forClass(ApiToDo.class).verify();
    }
}
