package com.datasift.dropwizard.zeromq.socket;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.util.List;

/**
 * A factory for a ZeroMQ SUB socket.
 *
 * @see ZeroMQSocketFactory
 */
@JsonTypeName("sub")
public class ZeroMQSubSocketFactory extends BaseZeroMQSocketFactory {

    @NotNull
    private List<String> topics = ImmutableList.of();

    @JsonProperty("topics")
    public List<String> getTopics() {
        return topics;
    }

    @JsonProperty("topics")
    public void setTopics(final List<String> topics) {
        this.topics = topics;
    }

    public ZMQ.Socket build(final ZContext context) {
        final ZMQ.Socket socket = build(context, ZMQ.SUB);

        // subscribe to configured topics
        for (final String topic : topics) {
            socket.subscribe(topic.getBytes(Charsets.UTF_8));
        }

        return socket;
    }
}
