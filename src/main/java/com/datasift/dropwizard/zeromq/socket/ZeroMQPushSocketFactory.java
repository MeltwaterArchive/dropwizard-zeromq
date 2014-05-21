package com.datasift.dropwizard.zeromq.socket;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

/**
 * A factory for a ZeroMQ PUSH socket.
 *
 * @see ZeroMQSocketFactory
 */
@JsonTypeName("push")
class ZeroMQPushSocketFactory extends BaseZeroMQSocketFactory {

    public ZMQ.Socket build(final ZContext context) {
        return build(context, ZMQ.PUSH);
    }

    public ZMQ.Socket connect(final ZContext context) {
        return connect(build(context));
    }

    public ZMQ.Socket bind(final ZContext context) {
        return bind(build(context));
    }
}
