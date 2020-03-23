package blazeDemo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import blazeDemo.db.MongoDBFactory;
import blazeDemo.db.MongoDBManager;
import blazeDemo.db.daos.CustomerDAO;
import blazeDemo.health.BlazeDemoDBHealthCheck;
import blazeDemo.resources.CustomerResource;

//cors
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.FilterRegistration;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class blazeDemoApplication extends Application<blazeDemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new blazeDemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "blazeDemo";
    }

    @Override
    public void initialize(final Bootstrap<blazeDemoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final blazeDemoConfiguration configuration,
                    final Environment environment) {
        
        //enable cors:
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        
        
        //setup mongo connection
        MongoDBFactory mongoDBManagerConn = new MongoDBFactory(configuration.getMongoDBConnection());
        MongoDBManager mongoDBManaged = new MongoDBManager(mongoDBManagerConn.getClient());
        
        CustomerDAO customerDAO = new CustomerDAO(mongoDBManagerConn.getClient()
                                        .getDatabase(configuration.getMongoDBConnection().getDatabase())
                                        .getCollection("customers"));
        
        environment.lifecycle().manage(mongoDBManaged);
        environment.jersey().register(new CustomerResource(customerDAO));
        environment.healthChecks().register("BlazeDemoDBHealthCheck", new BlazeDemoDBHealthCheck(mongoDBManagerConn.getClient()));
    }

}
