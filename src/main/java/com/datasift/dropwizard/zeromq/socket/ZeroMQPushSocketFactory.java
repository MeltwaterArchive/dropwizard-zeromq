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
public class ZeroMQPushSocketFactory extends BaseZeroMQSocketFactory {

    public ZMQ.Socket build(final ZContext context) {
        return build(context, ZMQ.PUSH);
    }
}
