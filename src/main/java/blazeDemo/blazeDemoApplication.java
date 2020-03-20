package blazeDemo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: implement application
    }

}
