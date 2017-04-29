package io.as.todo.store;

/**
 * Application constants.
 */
public final class AppConstants
{

    private AppConstants()
    {
    }

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    // to be used during tests; with some stubbed implementations; and in memory database
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_FAST = "fast";
    public static final String SYSTEM_ACCOUNT = "system";

}
