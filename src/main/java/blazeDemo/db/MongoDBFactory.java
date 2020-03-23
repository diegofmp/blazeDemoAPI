package blazeDemo.db;

import java.util.List;
import java.util.stream.Collectors;

import blazeDemo.db.configuration.Credentials;
import blazeDemo.db.configuration.MongoDBConnection;
import blazeDemo.db.configuration.Seed;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDBFactory {
    //private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBFactory.class);
    private MongoDBConnection mongoDBConnection; //connection config
    
    //constructor
    public MongoDBFactory(final MongoDBConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }
    
    //get mongoclient
    public MongoClient getClient() {
        //LOGGER.info("Creating mongoDB client.");
        final Credentials configCredentials = mongoDBConnection.getCredentials();

        final MongoCredential credentials = MongoCredential.createCredential(
                configCredentials.getUsername(),
                mongoDBConnection.getDatabase(),
                configCredentials.getPassword());

        final MongoClient client = MongoClients.create(
                MongoClientSettings.builder()
                        .credential(credentials)
                        .applyToClusterSettings(builder -> builder.hosts(getServers())).build()
        );

        return client;
    }

    
    //get a list of servers from seeds
    private List<ServerAddress> getServers() {
        final List<Seed> seeds = mongoDBConnection.getSeeds();
        return seeds.stream()
                .map(seed -> {
                    final ServerAddress serverAddress = new ServerAddress(seed.getHost(), seed.getPort());
                    return serverAddress;
                })
                .collect(Collectors.toList());
    }
    
}
