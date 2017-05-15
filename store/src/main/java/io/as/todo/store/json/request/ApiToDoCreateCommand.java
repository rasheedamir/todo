package io.as.todo.store.json.request;

import io.as.todo.store.json.Command;
import lombok.Value;

@Value
public class ApiToDoCreateCommand implements Command
{
    private final String title;
}
