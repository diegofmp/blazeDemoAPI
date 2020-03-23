package blazeDemo.db.configuration;
import java.util.List;

public class MongoDBConnection {
    private Credentials credentials;
    private List<Seed> seeds; //list of seeds
    private String database; //database name

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
    
    //get mongo connection config as string
    @Override
    public String toString() {
        return "MongoDBConnection{"
                + "credentials=" + credentials
                + ", seeds=" + seeds
                + ", database='" + database + '\''
                + '}';
    }
    
    
}
