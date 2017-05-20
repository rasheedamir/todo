package io.as.todo.store.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@SuppressWarnings("ALL")
public class SwaggerConfiguration
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfiguration.class);

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    @Bean
    public Docket swaggerSpringfoxDocket(ToDoProperties properties)
    {
        LOGGER.debug("Starting Swagger");

        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
            properties.getSwagger().getContactName(),
            properties.getSwagger().getContactUrl(),
            properties.getSwagger().getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
            properties.getSwagger().getTitle(),
            properties.getSwagger().getDescription(),
            properties.getSwagger().getVersion(),
            properties.getSwagger().getTermsOfServiceUrl(),
            contact,
            properties.getSwagger().getLicense(),
            properties.getSwagger().getLicenseUrl());

        /*
            Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo)
            .forCodeGeneration(true)
            .genericModelSubstitutes(ResponseEntity.class)
            .ignoredParameterTypes(java.sql.Date.class)
            .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
            .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
            .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
            .select()
            .paths(regex(DEFAULT_INCLUDE_PATTERN))
            .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
         */

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();

        watch.stop();
        LOGGER.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());

        return docket;
    }
}
