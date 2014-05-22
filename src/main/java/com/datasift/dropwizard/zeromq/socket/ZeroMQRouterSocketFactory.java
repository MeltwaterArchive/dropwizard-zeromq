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
public class ZeroMQRouterSocketFactory extends BaseZeroMQSocketFactory {

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
        final ZMQ.Socket socket = build(context, ZMQ.ROUTER);
        socket.setRouterMandatory(isMandatory());
        return socket;
    }
}
