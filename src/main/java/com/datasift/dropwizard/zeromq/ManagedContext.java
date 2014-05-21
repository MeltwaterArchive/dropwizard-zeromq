package com.datasift.dropwizard.zeromq;

import io.dropwizard.lifecycle.Managed;
import org.zeromq.ZContext;

/** TODO: Document */
class ManagedContext implements Managed {

    private final ZContext context;

    public ManagedContext(final ZContext context) {
        this.context = context;
    }

    @Override
    public void start() throws Exception {
        // nothing to do
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}
