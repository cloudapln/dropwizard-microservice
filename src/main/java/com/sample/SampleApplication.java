package com.sample;


import com.sample.healthcheck.TemplateHealthCheck;
import com.sample.resource.SampleResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class SampleApplication extends Application<SampleConfiguration> {


    public static void main(String[] args) throws Exception {
        new SampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
        // todo
    }

    @Override
    public void run(SampleConfiguration configuration,
                    Environment environment) {
        final SampleResource resource = new SampleResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}


