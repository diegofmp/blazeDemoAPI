package blazeDemo.db.configuration;
import java.util.Objects;

public class Seed {
    private String host;

    
    private int port;
    
    //constructor
    public Seed() {
    }
    
    //setters & getters
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    
    //functions to handle seed
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Seed seed = (Seed) o;
        return port == seed.port &&
                Objects.equals(host, seed.host);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }

    @Override
    public String toString() {
        return "Seed{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
    
}
