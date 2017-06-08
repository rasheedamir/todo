package io.as.todo.store;

import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ActiveProfiles(AppConstants.SPRING_PROFILE_TEST) //this forces application-test.yml & bootstrap-test.yml to be loaded
public @interface TestProfile
{

}
