package com.datasift.dropwizard.zeromq.socket;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.setup.Environment;
import io.dropwizard.util.Size;
import io.dropwizard.util.Duration;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.valuehandling.UnwrapValidatedValue;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZContext;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import java.net.URI;
import java.util.Set;

/**
 * A factory for a ZeroMQ Socket.
 */
public abstract class BaseZeroMQSocketFactory implements ZeroMQSocketFactory {

    @NotEmpty
    private Set<URI> endpoints = ImmutableSet.of();

    @Min(0)
    private long affinity = 0L;

    @Min(0)
    private long backlog = 100;

    private boolean delayAttachOnConnect = false;

    @Min(0)
    private long highWaterMark = 1000L;

    @Min(0)
    private long receiveHighWaterMark = 1000;

    @Min(0)
    private long sendHighWaterMark = 1000;

//    @Length(min = 1, max = 255)
//    @UnwrapValidatedValue
    private Optional<String> identity = Optional.absent();

    private boolean ipv4Only = true;

    @Min(-1)
    private long linger = -1L;

    private Optional<Size> maxMessageSize = Optional.absent();

    @Min(1)
    private long multicastHops = 1L;

    private Duration multicastRecoveryInterval = Duration.seconds(10);

    @Min(1)
    private long maxMulticastRate = 100L;

    private Optional<Size> receiveBufferSize = Optional.absent();

    private Optional<Size> sendBufferSize = Optional.absent();

    private Optional<Duration> receiveTimeout = Optional.absent();

    private Optional<Duration> sendTimeout = Optional.absent();

    private Optional<Duration> initialReconnectInterval = Optional.of(Duration.milliseconds(100));

    private Optional<Duration> maxReconnectInterval = Optional.absent();

    private Optional<Boolean> tcpKeepAlive = Optional.absent();

//    @Min(1)
//    @UnwrapValidatedValue
    private Optional<Long> tcpKeepAliveCount = Optional.absent();

//    @Min(1)
//    @UnwrapValidatedValue
    private Optional<Long> tcpKeepAliveIdle = Optional.absent();

//    @Min(1)
//    @UnwrapValidatedValue
    private Optional<Long> tcpKeepAliveInterval = Optional.absent();

    @JsonProperty
    public Set<URI> getEndpoints() {
        return endpoints;
    }

    @JsonProperty
    public void setEndpoints(final Set<URI> endpoints) {
        this.endpoints = endpoints;
    }

    @JsonProperty
    public long getAffinity() {
        return affinity;
    }

    @JsonProperty
    public void setAffinity(final long affinity) {
        this.affinity = affinity;
    }

    @JsonProperty
    public long getBacklog() {
        return backlog;
    }

    @JsonProperty
    public void setBacklog(final long backlog) {
        this.backlog = backlog;
    }

    @JsonProperty
    public boolean isDelayAttachOnConnect() {
        return delayAttachOnConnect;
    }

    @JsonProperty
    public void setDelayAttachOnConnect(final boolean delayAttachOnConnect) {
        this.delayAttachOnConnect = delayAttachOnConnect;
    }

    @JsonProperty
    public long getHighWaterMark() {
        return highWaterMark;
    }

    @JsonProperty
    public void setHighWaterMark(final long highWaterMark) {
        this.highWaterMark = highWaterMark;
    }

    @JsonProperty
    public long getReceiveHighWaterMark() {
        return receiveHighWaterMark;
    }

    @JsonProperty
    public void setReceiveHighWaterMark(final long receiveHighWaterMark) {
        this.receiveHighWaterMark = receiveHighWaterMark;
    }

    @JsonProperty
    public long getSendHighWaterMark() {
        return sendHighWaterMark;
    }

    @JsonProperty
    public void setSendHighWaterMark(final long sendHighWaterMark) {
        this.sendHighWaterMark = sendHighWaterMark;
    }

    @JsonProperty
    public Optional<String> getIdentity() {
        return identity;
    }

    @JsonProperty
    public void setIdentity(final Optional<String> identity) {
        this.identity = identity;
    }

    @JsonProperty
    public boolean isIpv4Only() {
        return ipv4Only;
    }

    @JsonProperty
    public void setIpv4Only(final boolean ipv4Only) {
        this.ipv4Only = ipv4Only;
    }

    @JsonProperty
    public long getLinger() {
        return linger;
    }

    @JsonProperty
    public void setLinger(final long linger) {
        this.linger = linger;
    }

    @JsonProperty
    public Optional<Size> getMaxMessageSize() {
        return maxMessageSize;
    }

    @JsonProperty
    public void setMaxMessageSize(final Optional<Size> maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    @JsonProperty
    public long getMulticastHops() {
        return multicastHops;
    }

    @JsonProperty
    public void setMulticastHops(final long multicastHops) {
        this.multicastHops = multicastHops;
    }

    @JsonProperty
    public Duration getMulticastRecoveryInterval() {
        return multicastRecoveryInterval;
    }

    @JsonProperty
    public void setMulticastRecoveryInterval(final Duration multicastRecoveryInterval) {
        this.multicastRecoveryInterval = multicastRecoveryInterval;
    }

    @JsonProperty
    public long getMaxMulticastRate() {
        return maxMulticastRate;
    }

    @JsonProperty
    public void setMaxMulticastRate(final long maxMulticastRate) {
        this.maxMulticastRate = maxMulticastRate;
    }

    @JsonProperty
    public Optional<Size> getReceiveBufferSize() {
        return receiveBufferSize;
    }

    @JsonProperty
    public void setReceiveBufferSize(final Optional<Size> receiveBufferSize) {
        this.receiveBufferSize = receiveBufferSize;
    }

    @JsonProperty
    public Optional<Size> getSendBufferSize() {
        return sendBufferSize;
    }

    @JsonProperty
    public void setSendBufferSize(final Optional<Size> sendBufferSize) {
        this.sendBufferSize = sendBufferSize;
    }

    @JsonProperty
    public Optional<Duration> getReceiveTimeout() {
        return receiveTimeout;
    }

    @JsonProperty
    public void setReceiveTimeout(final Optional<Duration> receiveTimeout) {
        this.receiveTimeout = receiveTimeout;
    }

    @JsonProperty
    public Optional<Duration> getSendTimeout() {
        return sendTimeout;
    }

    @JsonProperty
    public void setSendTimeout(final Optional<Duration> sendTimeout) {
        this.sendTimeout = sendTimeout;
    }

    @JsonProperty
    public Optional<Duration> getInitialReconnectInterval() {
        return initialReconnectInterval;
    }

    @JsonProperty
    public void setInitialReconnectInterval(final Optional<Duration> initialReconnectInterval) {
        this.initialReconnectInterval = initialReconnectInterval;
    }

    @JsonProperty
    public Optional<Duration> getMaxReconnectInterval() {
        return maxReconnectInterval;
    }

    @JsonProperty
    public void setMaxReconnectInterval(final Optional<Duration> maxReconnectInterval) {
        this.maxReconnectInterval = maxReconnectInterval;
    }

    @JsonProperty
    public Optional<Boolean> getTcpKeepAlive() {
        return tcpKeepAlive;
    }

    @JsonProperty
    public void setTcpKeepAlive(final Optional<Boolean> tcpKeepAlive) {
        this.tcpKeepAlive = tcpKeepAlive;
    }

    @JsonProperty
    public Optional<Long> getTcpKeepAliveCount() {
        return tcpKeepAliveCount;
    }

    @JsonProperty
    public void setTcpKeepAliveCount(final Optional<Long> tcpKeepAliveCount) {
        this.tcpKeepAliveCount = tcpKeepAliveCount;
    }

    @JsonProperty
    public Optional<Long> getTcpKeepAliveIdle() {
        return tcpKeepAliveIdle;
    }

    @JsonProperty
    public void setTcpKeepAliveIdle(final Optional<Long> tcpKeepAliveIdle) {
        this.tcpKeepAliveIdle = tcpKeepAliveIdle;
    }

    @JsonProperty
    public Optional<Long> getTcpKeepAliveInterval() {
        return tcpKeepAliveInterval;
    }

    @JsonProperty
    public void setTcpKeepAliveInterval(final Optional<Long> tcpKeepAliveInterval) {
        this.tcpKeepAliveInterval = tcpKeepAliveInterval;
    }

    @Override
    public Socket build(final ZContext context, final Environment environment) {
        final Socket socket = build(context);
        environment.lifecycle().manage(new ManagedSocket(context, socket));
        return socket;
    }

    public Socket connect(final ZContext context) {
        return connect(build(context));
    }

    public Socket connect(final ZContext context, final Environment environment) {
        return connect(build(context, environment));
    }

    public Socket bind(final ZContext context) {
        return bind(build(context));
    }

    public Socket bind(final ZContext context, final Environment environment) {
        return bind(build(context, environment));
    }

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
        socket.setAffinity(getAffinity());
        socket.setBacklog(getBacklog());
//        socket.setDelayAttachOnConnect(isDelayAttachOnConnect);
        socket.setHWM(getHighWaterMark());
        socket.setRcvHWM(getReceiveHighWaterMark());
        socket.setSndHWM(getSendHighWaterMark());
        socket.setIPv4Only(isIpv4Only());
        socket.setLinger(getLinger());
        socket.setMulticastHops(getMulticastHops());
        socket.setRecoveryInterval(getMulticastRecoveryInterval().toMilliseconds());
        socket.setRate(getMaxMulticastRate());

        if (getIdentity().isPresent()) {
            socket.setIdentity(getIdentity().get().getBytes(Charsets.US_ASCII));
        }

        if (getMaxMessageSize().isPresent()) {
            socket.setMaxMsgSize(getMaxMessageSize().get().toBytes());
        }

        socket.setReceiveBufferSize(
                getReceiveBufferSize().isPresent()
                        ? getReceiveBufferSize().get().toBytes()
                        : 0);

        socket.setSendBufferSize(
                getSendBufferSize().isPresent()
                        ? getSendBufferSize().get().toBytes()
                        : 0);

        socket.setReceiveTimeOut(
                getReceiveTimeout().isPresent()
                        ? (int) getReceiveTimeout().get().toMilliseconds()
                        : -1);

        socket.setSendTimeOut(
                getSendTimeout().isPresent()
                        ? (int) getSendTimeout().get().toMilliseconds()
                        : -1);

        socket.setReconnectIVL(
                getInitialReconnectInterval().isPresent()
                        ? getInitialReconnectInterval().get().toMilliseconds()
                        : -1);

        socket.setReconnectIVLMax(
                getMaxReconnectInterval().isPresent()
                        ? getMaxReconnectInterval().get().toMilliseconds()
                        : 0);

        socket.setTCPKeepAlive(
                getTcpKeepAlive().isPresent()
                        ? (getTcpKeepAlive().get() ? 1 : 0)
                        : -1);

        socket.setTCPKeepAliveCount(
                getTcpKeepAliveCount().isPresent()
                        ? getTcpKeepAliveCount().get()
                        : -1);

        socket.setTCPKeepAliveIdle(
                getTcpKeepAliveIdle().isPresent()
                        ? getTcpKeepAliveIdle().get()
                        : -1);

        socket.setTCPKeepAliveInterval(
                getTcpKeepAliveInterval().isPresent()
                        ? getTcpKeepAliveInterval().get()
                        : -1);

        return socket;
    }
}
