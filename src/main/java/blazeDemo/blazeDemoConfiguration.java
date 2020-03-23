package blazeDemo;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import blazeDemo.db.configuration.MongoDBConnection;

public class blazeDemoConfiguration extends Configuration {
    // TODO: implement service configuration
    @NotNull
    @JsonProperty
    private MongoDBConnection mongoDBConnection;

    public MongoDBConnection getMongoDBConnection() {
        return mongoDBConnection;
    }

    public void setMongoDBConnection(MongoDBConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }        
}
