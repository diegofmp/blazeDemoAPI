package blazeDemo;
import blazeDemo.db.MongoDBFactory;
import blazeDemo.db.MongoDBManager;
import blazeDemo.db.daos.CustomerDAO;
import blazeDemo.health.BlazeDemoDBHealthCheck;
import blazeDemo.resources.CustomerResource;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

public class blazeDemoModule extends DropwizardAwareModule<blazeDemoConfiguration> {

  @Override
  public void configure() { 
    //shared objects
    MongoDBFactory mongoDBManagerConn = new MongoDBFactory(configuration().getMongoDBConnection());
    MongoDBManager mongoDBManaged = new MongoDBManager(mongoDBManagerConn.getClient());
    CustomerDAO customerDAO = new CustomerDAO(mongoDBManagerConn.getClient()
        .getDatabase(configuration().getMongoDBConnection().getDatabase())
        .getCollection("customers"));
    
    if (configuration().bindResources()) {
        //instanciate customer resource   
        CustomerResource customerR = new CustomerResource(customerDAO);
        binder().bind(CustomerResource.class).toInstance(customerR);
    }
    
    if (configuration().bindFilters()) {
        //instanciate cors wildcard filter
        final FilterRegistration.Dynamic cors =
            environment().servlets().addFilter("CORS", CrossOriginFilter.class);
        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        binder().bind(FilterRegistration.class).toInstance(cors);
    }

    if (configuration().bindExtras()) {
        //database
        binder().bind(MongoDBManager.class).toInstance(mongoDBManaged);
        
        //healthcheck
        BlazeDemoDBHealthCheck heatlhCheck = new BlazeDemoDBHealthCheck(mongoDBManagerConn.getClient());
        binder().bind(BlazeDemoDBHealthCheck.class).toInstance(heatlhCheck);
    }
  }
}