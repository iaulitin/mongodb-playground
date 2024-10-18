package org.iaulitin.mongodbpg.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.iaulitin.mongodbpg.dao")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String dbUri;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    // This is required to be provided per https://docs.spring.io/spring-data/mongodb/reference/mongodb/client-session-transactions.html
    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    // This has to be overriden for some reason
    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    // after adding the current configuration class the authentication broke. Registering this bean fixes it:  https://stackoverflow.com/questions/62819386/query-failed-with-error-code-13-and-error-message-command-find-requires-authent
    @Bean
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(dbUri);
    }

}