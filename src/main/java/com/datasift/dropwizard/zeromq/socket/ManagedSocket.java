package com.datasift.dropwizard.zeromq.socket;

import io.dropwizard.lifecycle.Managed;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/** TODO: Document */
class ManagedSocket implements Managed {

    private final ZContext context;
    private final ZMQ.Socket socket;

    public ManagedSocket(final ZContext context, final ZMQ.Socket socket) {
        this.context = context;
        this.socket = socket;
    }

    @Override
    public void start() throws Exception {
        // nothing to do
    }

    @Override
    public void stop() throws Exception {
        context.destroySocket(socket);
    }
}
