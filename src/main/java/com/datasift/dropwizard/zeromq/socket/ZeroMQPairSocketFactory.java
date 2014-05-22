package com.datasift.dropwizard.zeromq.socket;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * A factory for a ZeroMQ PAIR socket.
 *
 * @see ZeroMQSocketFactory
 */
@JsonTypeName("pair")
class ZeroMQPairSocketFactory extends BaseZeroMQSocketFactory {

    public ZMQ.Socket build(final ZContext context) {
        return build(context, ZMQ.PAIR);
    }
}
