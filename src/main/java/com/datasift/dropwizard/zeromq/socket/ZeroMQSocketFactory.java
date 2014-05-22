package com.datasift.dropwizard.zeromq.socket;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.setup.Environment;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface ZeroMQSocketFactory {

    public ZMQ.Socket build(ZContext context, Environment environment);
    public ZMQ.Socket connect(ZContext context, Environment environment);
    public ZMQ.Socket bind(ZContext context, Environment environment);
}
