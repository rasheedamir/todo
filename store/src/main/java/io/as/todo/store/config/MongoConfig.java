package io.as.todo.store.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 5/26/17.
 */
@RefreshScope
@Component
public class MongoConfig extends AbstractMongoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoConfig.class);

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host:mongo}")
    private String databaseHost;
    @Value("${spring.data.mongodb.port:27017}")
    private Integer databasePort;

    @Value("${service.mongo.mongo:mongo:27017}")
    private String consulMongoUri;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public @Bean @RefreshScope Mongo mongo() throws Exception {
        LOGGER.info("Mongo value from Consul: " + consulMongoUri);
        List<ServerAddress> mongoServices = new ArrayList<ServerAddress>();
        if(consulMongoUri.isEmpty()) {
            mongoServices.add(new ServerAddress(databaseHost, databasePort));
        }
        else {
            mongoServices.add(new ServerAddress(consulMongoUri.split(":")[0],
                                                Integer.parseInt(consulMongoUri.split(":")[1])));
        }
        return new MongoClient(mongoServices);
    }

    @Override
    public @Bean @RefreshScope MongoTemplate mongoTemplate() throws Exception {
        Mongo mongo = mongo();
        LOGGER.info("Creating Mongo Template for: " + mongo.getServerAddressList() + " and database "+getDatabaseName());
        return new MongoTemplate(mongo, getDatabaseName());
    }

}
