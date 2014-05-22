package com.datasift.dropwizard.zeromq.socket;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * A factory for a ZeroMQ ROUTER socket.
 *
 * @see ZeroMQSocketFactory
 */
@JsonTypeName("router")
class ZeroMQRouterSocketFactory extends BaseZeroMQSocketFactory {

    private boolean mandatory = false;

    @JsonProperty
    public boolean isMandatory() {
        return mandatory;
    }

    @JsonProperty
    public void setMandatory(final boolean isMandatory) {
        this.mandatory = isMandatory;
    }

    public ZMQ.Socket build(final ZContext context) {
        return build(context, ZMQ.ROUTER);
    }

    public ZMQ.Socket connect(final ZContext context) {
        return connect(build(context));
    }

    public ZMQ.Socket bind(final ZContext context) {
        return bind(build(context));
    }

    @Override
    protected ZMQ.Socket configure(final ZMQ.Socket socket) {
        super.configure(socket).setRouterMandatory(mandatory);
        return socket;
    }
}
