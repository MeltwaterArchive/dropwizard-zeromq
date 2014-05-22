package com.datasift.dropwizard.zeromq.socket;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * A factory for a ZeroMQ REQ socket.
 *
 * @see ZeroMQSocketFactory
 */
@JsonTypeName("req")
public class ZeroMQReqSocketFactory extends BaseZeroMQSocketFactory {

    public ZMQ.Socket build(final ZContext context) {
        return build(context, ZMQ.REQ);
    }
}
