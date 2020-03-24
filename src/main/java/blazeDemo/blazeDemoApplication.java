package blazeDemo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

//cors
import ru.vyarus.dropwizard.guice.GuiceBundle;

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
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new blazeDemoModule())
                .build());
    }

    @Override
    public void run(final blazeDemoConfiguration configuration,
                    final Environment environment) {        
    }

}
