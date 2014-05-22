package com.datasift.dropwizard.zeromq.socket;

import com.datasift.dropwizard.zeromq.ManagedSocket;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.setup.Environment;
import io.dropwizard.util.Size;
import io.dropwizard.util.Duration;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZContext;

import javax.validation.constraints.Min;

import java.net.URI;
import java.util.Set;

/**
 * A factory for a ZeroMQ Socket.
 */
public abstract class BaseZeroMQSocketFactory implements ZeroMQSocketFactory {

    @JsonProperty
    @NotEmpty
    private Set<URI> endpoints = ImmutableSet.of();

    @JsonProperty
    @Min(0)
    private long affinity = 0L;

    @JsonProperty
    @Min(0)
    private long backlog = 100;

    @JsonProperty
    private boolean delayAttachOnConnect = false;

    @JsonProperty
    @Min(0)
    private long highWaterMark = 1000L;

    @JsonProperty
    @Min(0)
    private long receiveHighWaterMark = 1000;

    @JsonProperty
    @Min(0)
    private long sendHighWaterMark = 1000;

    @JsonProperty
    @Length(min = 1, max = 255)
    private Optional<String> identity = Optional.absent();

    @JsonProperty
    private boolean ipv4Only = true;

    @JsonProperty
    @Min(-1)
    private long linger = -1L;

    @JsonProperty
    private Optional<Size> maxMessageSize = Optional.absent();

    @JsonProperty
    @Min(1)
    private long multicastHops = 1L;

    @JsonProperty
    private Duration multicastRecoveryInterval = Duration.seconds(10);

    @JsonProperty
    @Min(1)
    private long maxMulticastRate = 100L;

    @JsonProperty
    private Optional<Size> receiveBufferSize = Optional.absent();

    @JsonProperty
    private Optional<Size> sendBufferSize = Optional.absent();

    @JsonProperty
    private Optional<Duration> receiveTimeout = Optional.absent();

    @JsonProperty
    private Optional<Duration> sendTimeout = Optional.absent();

    @JsonProperty
    private Optional<Duration> initialReconnectInterval = Optional.of(Duration.milliseconds(100));

    @JsonProperty
    private Optional<Duration> maxReconnectInterval = Optional.absent();

    @JsonProperty
    private Optional<Boolean> tcpKeepAlive = Optional.absent();

    @JsonProperty
    @Min(1)
    private Optional<Long> tcpKeepAliveCount = Optional.absent();

    @JsonProperty
    @Min(1)
    private Optional<Long> tcpKeepAliveIdle = Optional.absent();

    @JsonProperty
    @Min(1)
    private Optional<Long> tcpKeepAliveInterval = Optional.absent();

    @Override
    public Socket build(final ZContext context, final Environment environment) {
        final Socket socket = build(context);
        environment.lifecycle().manage(new ManagedSocket(socket));
        return socket;
    }

    abstract protected Socket build(final ZContext context);

    protected Socket build(final ZContext context, final int type) {
        return configure(context.createSocket(type));
    }

    protected Socket connect(final Socket socket) {
        for (final URI endpoint : endpoints) {
            socket.connect(endpoint.toString());
        }
        return socket;
    }

    protected Socket bind(final Socket socket) {
        for (final URI endpoint : endpoints) {
            socket.bind(endpoint.toString());
        }
        return socket;
    }

    protected Socket configure(final Socket socket) {
        socket.setAffinity(affinity);
        socket.setBacklog(backlog);
//        socket.setDelayAttachOnConnect(delayAttachOnConnect);
        socket.setHWM(highWaterMark);
        socket.setRcvHWM(receiveHighWaterMark);
        socket.setSndHWM(sendHighWaterMark);
        socket.setIPv4Only(ipv4Only);
        socket.setLinger(linger);
        socket.setMulticastHops(multicastHops);
        socket.setRecoveryInterval(multicastRecoveryInterval.toMilliseconds());
        socket.setRate(maxMulticastRate);

        if (identity.isPresent()) {
            socket.setIdentity(identity.get().getBytes(Charsets.US_ASCII));
        }

        if (maxMessageSize.isPresent()) {
            socket.setMaxMsgSize(maxMessageSize.get().toBytes());
        }

        socket.setReceiveBufferSize(
                receiveBufferSize.isPresent()
                        ? receiveBufferSize.get().toBytes()
                        : 0);

        socket.setSendBufferSize(
                sendBufferSize.isPresent()
                        ? sendBufferSize.get().toBytes()
                        : 0);

        socket.setReceiveTimeOut(
                receiveTimeout.isPresent()
                        ? (int) receiveTimeout.get().toMilliseconds()
                        : -1);

        socket.setSendTimeOut(
                sendTimeout.isPresent()
                        ? (int) sendTimeout.get().toMilliseconds()
                        : -1);

        socket.setReconnectIVL(
                initialReconnectInterval.isPresent()
                        ? initialReconnectInterval.get().toMilliseconds()
                        : -1);

        socket.setReconnectIVLMax(
                maxReconnectInterval.isPresent()
                        ? maxReconnectInterval.get().toMilliseconds()
                        : 0);

        socket.setTCPKeepAlive(
                tcpKeepAlive.isPresent()
                        ? (tcpKeepAlive.get() ? 1 : 0)
                        : -1);

        socket.setTCPKeepAliveCount(
                tcpKeepAliveCount.isPresent()
                        ? tcpKeepAliveCount.get()
                        : -1);

        socket.setTCPKeepAliveIdle(
                tcpKeepAliveIdle.isPresent()
                        ? tcpKeepAliveIdle.get()
                        : -1);

        socket.setTCPKeepAliveInterval(
                tcpKeepAliveInterval.isPresent()
                        ? tcpKeepAliveInterval.get()
                        : -1);

        return socket;
    }
}
