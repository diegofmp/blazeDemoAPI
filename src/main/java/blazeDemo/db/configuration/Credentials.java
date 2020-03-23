package blazeDemo.db.configuration;

import java.util.Arrays;
import java.util.Objects;

import blazeDemo.util.PasswordSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Credentials {
    private String username;
    @JsonSerialize(using = PasswordSerializer.class)
    private char[] password;
    
    
    //constructor
    public Credentials() {
    }
    
    //functions to handle credentials    
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Credentials that = (Credentials) o;
        return Objects.equals(username, that.username) &&
                Arrays.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }
    
    //setters & getters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
    
    //get credentials chain as string
    @Override
    public String toString() {
        return "Credentials{"
                + "username='" + username + '\''
                + ", password=" + Arrays.toString(password)
                + '}';
    }                
}
