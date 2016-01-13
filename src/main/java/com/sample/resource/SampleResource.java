package com.sample.resource;

import com.codahale.metrics.annotation.Timed;
import com.sample.api.Saying;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello")
@Api("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public SampleResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @ApiOperation(
            value = "Say Hello Endpoint",
            notes = "Dropwizard demonstration.",
            response = Saying.class)
    public Saying sayHello(@QueryParam("name") String name) {

        String templateValue = defaultName;
        if (!StringUtils.isEmpty(name)) templateValue = name;

        final String value = String.format(template, templateValue);
        return new Saying(counter.incrementAndGet(), value);
    }
}