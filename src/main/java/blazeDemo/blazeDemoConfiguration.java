package blazeDemo;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import blazeDemo.db.configuration.MongoDBConnection;

public class blazeDemoConfiguration extends Configuration {
    private boolean bindResources = true;
    private boolean bindExtras = true;
    private boolean bindFilters = true;
      
    @JsonProperty("bindResources")
    public boolean bindResources() {
      return bindResources;
    }

    public void setBindResources(boolean bindResources) {
      this.bindResources = bindResources;
    }

    @JsonProperty("bindFilters")
    public boolean bindFilters() {
      return bindFilters;
    }

    public void setBindFilters(boolean bindFilters) {
      this.bindFilters = bindFilters;
    }

    @JsonProperty("bindExtras")
    public boolean bindExtras() {
      return bindExtras;
    }

    public void setBindExtras(boolean bindExtras) {
      this.bindExtras = bindExtras;
    }
    
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
