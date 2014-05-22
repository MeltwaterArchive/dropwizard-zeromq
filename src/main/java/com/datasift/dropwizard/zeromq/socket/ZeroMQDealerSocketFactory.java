package com.datasift.dropwizard.zeromq.socket;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * A factory for a ZeroMQ DEALER socket.
 *
 * @see ZeroMQSocketFactory
 */
@JsonTypeName("dealer")
public class ZeroMQDealerSocketFactory extends BaseZeroMQSocketFactory {

    public ZMQ.Socket build(final ZContext context) {
        return build(context, ZMQ.DEALER);
    }
}
