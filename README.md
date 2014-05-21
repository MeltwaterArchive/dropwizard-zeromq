# Dropwizard ZeroMQ

This modules provides basic integration for [ZeroMQ](http://zeromq.org/) with 
Dropwizard. Both the native [JZMQ](http://github.com/zeromq/jzmq) and the 
pure-Java [JeroMQ](http://github.com/zeromq/jeromq) implementations are 
supported.

## Dependency

Add a dependency to your project:

```xml
<dependency>
    <groupId>com.datasift.dropwizard</groupId>
    <artifactId>dropwizard-zeromq</artifactId>
    <version>${dropwizard.version}-1-SNAPSHOT</version>
</dependency>
```

Where `${dropwizard.version}` is the version of Dropwizard you're using (e.g. 
`0.7.0`).

You will also need to explicitly select a ZeroMQ binding, add **ONE** of the 
following:

### JZMQ

JZMQ is a JNI wrapper around the native libzmq API. As such, you will need to 
ensure that the `libjzmq.so` has been properly compiled and is available to 
your application.

```xml
<dependency>
    <groupId>org.zeromq</groupId>
    <artifactId>jzmq</artifactId>
    <version>3.0.1</version>
</dependency>
```

### JeroMQ

JeroMQ is a pure-Java implementation of the ZeroMQ protocol. It's developed in 
parallel to the mainline ZeroMQ project.

```xml
<dependency>
    <groupId>org.zeromq</groupId>
    <artifactId>jeromq</artifactId>
    <version>0.3.4</version>
</dependency>
```

You must include exactly one of the above.

## Usage

To work with a ZeroMQ context in your Dropwizard application, first add the 
necessary configuration to your applications' `Configuration` class:

```java
public class MyConfiguration extends Configuration {

    @NotNull
    @Valid
    private ZeroMQFactory zeromq = new ZeroMQFactory();

    @JsonProperty("zeromq")
    public ZeroMQFactory getZeroMQFactory() {
        return zeromq;
    }

    @JsonProperty("zeromq")
    public void setZeroMQFactory(ZeroMQFactory zeromq) {
        this.zeromq = zeromq;
    }
}
```

Then, in your application, build a `ZContext`:

```java
public class MyApplication extends Application<MyConfiguration> {
    
    @Override
    public void run(MyConfiguration configuration, Environment environment) {
        ZContext context = configuration.getZeroMQFactory().build(environment);
    }
}
```

To configure ZeroMQ `Socket`s, you'll want to include a `ZeroMQSocketFactory`:

```java
public MyConfiguration extends Configuration {
    
    // elided: context configuration
    
    @NotNull
    @Valid
    private ZeroMQSocketFactory socket = new ZeroMQPushSocketFactory();

    @JsonProperty("socket")
    public ZeroMQSocketFactory getSocket() {
        return socket;
    }

    @JsonProperty("socket")
    public void setSocket(ZeroMQSocketFactory socket) {
        this.socket = socket;
    }
}
```

In your YAML, you can define the full socket configuration, including the 
`type` of the `Socket`. `ZeroMQSocketFactory` uses the same SPI mechanism as
`AppenderFactory` (logging), `ServerFactory` and `ConnectorFactory` 
(web server); so the configuration will look familiar:

```yaml
zeromq:
  ioThreads: 3

socket:
  type: push
  endpoints:
    - tcp://localhost:1234
  highWaterMark: 50000
  sendTimeout: 100ms
```

If you want to mandate a particular type of socket in your application, make 
this explicit in the type signature of its configuration:

```java
public MyConfiguration extends Configuration {
    
    // elided: context configuration
    
    @NotNull
    @Valid
    private ZeroMQPushSocketFactory socket = new ZeroMQPushSocketFactory();
    //            ^^^^

    @JsonProperty("socket")
    public ZeroMQPushSocketFactory getSocket() {
    //           ^^^^
        return socket;
    }

    @JsonProperty("socket")
    public void setSocket(ZeroMQPushSocketFactory socket) {
    //                          ^^^^
        this.socket = socket;
    }
}
```

This will make the `type` property invalid, and only sockets of that type will be buildable.

