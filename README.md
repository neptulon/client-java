# Neptulon Java Client

[![Build Status](https://travis-ci.org/neptulon/client-java.svg?branch=master)](https://travis-ci.org/neptulon/client-java)

Neptulon client implementation for Java. Also works on Android. Following are required:

* Java 1.7+ for Java apps.
* Android 4.1+ for Android apps.

Depends on following libraries:

* OkHttp for WebSockets
* GSON for JSON serialization.

## Example

```java
Conn conn = new ConnImpl("ws://127.0.0.1:3000");
conn.middleware(new Logger());
conn.connect(new ConnCallback() { ... });
conn.sendRequest("hello", new EchoMessage("Hello from Java client!"), new ResCallback() {
    @Override
    public void handleResponse(ResCtx ctx) {
        Object res = ctx.getResult(Object.class);
        System.out.println("Received 'echo' response: " + res);
        msgCounter.countDown();
    }
});
```

## Building

```bash
./gradlew build
```

## Testing

Start a Neptulon server at local address: `127.0.0.1:3001` and then:

```bash
./gradlew check
```

## License

[MIT](LICENSE)
