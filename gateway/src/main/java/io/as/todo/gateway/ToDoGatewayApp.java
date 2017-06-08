package io.as.todo.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.TimeZone;

@EnableAutoConfiguration
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ToDoGatewayApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ToDoGatewayApp.class);

    private Environment environment;

    @Autowired
    public ToDoGatewayApp(Environment environment)
    {
        this.environment = environment;
    }

    @PostConstruct
    public void initApplication()
    {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        if (environment.getActiveProfiles().length == 0)
        {
            LOGGER.warn("No Spring profile configured, running with default configuration");
        }
        else
        {
            LOGGER.info("Running with Spring profile(s) : {}", Arrays.toString(environment.getActiveProfiles()));
        }
    }

    /**
     * Main method, used to run the application.
     */
    public static void main(String[] args) throws UnknownHostException
    {
        SpringApplication app = new SpringApplication(ToDoGatewayApp.class);
        Environment env = app.run(args).getEnvironment();
        LOGGER.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}{}\n\t" +
                        "External: \thttp://{}:{}{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                env.getProperty("server.context-path"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.context-path"));
    }
}
