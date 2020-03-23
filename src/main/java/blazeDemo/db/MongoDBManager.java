package blazeDemo.db;

import com.mongodb.client.MongoClient;
import io.dropwizard.lifecycle.Managed;

public class MongoDBManager implements Managed{
    private MongoClient mongoClient;
    
    //constructor
    public MongoDBManager(final MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongoClient.close();
    }
}
