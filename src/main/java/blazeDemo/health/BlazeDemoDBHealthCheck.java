package blazeDemo.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.client.MongoClient;
import org.bson.Document;

public class BlazeDemoDBHealthCheck extends HealthCheck{
    private MongoClient mongoClient;
    
    //constructor
    public BlazeDemoDBHealthCheck(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
    
    @Override
    protected Result check() {
        try {
            final Document document = mongoClient.getDatabase("blaze_demo").runCommand(new Document("buildInfo", 1));
            if (document == null) {
                return Result.unhealthy("Can not get buildInfo.");
            }
        } catch (final Exception e) {
            return Result.unhealthy("Can not get information from database.");
        }
        return Result.healthy();
    }
}
