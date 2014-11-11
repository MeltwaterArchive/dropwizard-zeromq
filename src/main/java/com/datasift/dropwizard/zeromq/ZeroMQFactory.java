package com.datasift.dropwizard.zeromq;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.setup.Environment;
import org.zeromq.ZContext;

import javax.validation.constraints.Min;

/**
 * TODO: Document
 */
public class ZeroMQFactory {

    @Min(1)
    private int ioThreads = 1;

    @JsonProperty
    public int getIoThreads() {
        return ioThreads;
    }

    @JsonProperty
    public void setIoThreads(final int ioThreads) {
        this.ioThreads = ioThreads;
    }

    public ZContext build() {
        final ZContext context = new ZContext();
        context.setIoThreads(ioThreads);
        return context;
    }

    public ZContext build(final Environment environment) {
        final ZContext context = build();
        environment.lifecycle().manage(new ManagedContext(context));
        return context;
    }
}
