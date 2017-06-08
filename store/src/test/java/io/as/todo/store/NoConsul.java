package io.as.todo.store;

import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @NoConsul} is a class-level annotation that is used to
 * configure the inlined test properties to disable service discovery.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@TestPropertySource(properties = {"spring.cloud.bus.enabled=false", "spring.cloud.discovery.enabled=false"})
public @interface NoConsul
{

}
