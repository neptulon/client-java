# Neptulon Java Client

[![Build Status](https://travis-ci.org/neptulon/client-java.svg?branch=master)](https://travis-ci.org/neptulon/client-java)
[![Documentation Status](https://readthedocs.org/projects/neptulon-java-client/badge/?version=latest)](http://neptulon-java-client.readthedocs.org/en/latest/?badge=latest)

Neptulon client implementation for Java. Also works on Android. Following are required:

* Java 1.7+ for Java apps.
* Android 4.1+ for Android apps.

Depends on following libraries:

* OkHttp for WebSockets
* GSON for JSON serialization.

## Example

```java
Conn conn = new ConnImpl("ws://127.0.0.1:3001");
conn.middleware(new Logger());
conn.connect();
conn.sendRequest("hello", new EchoMessage("Hello from Java client!"), new ResHandler<Object>() {
  @Override
  public Class<Object> getType() {
    return Object.class;
  }

  @Override
  public void handler(Response<Object> res) {
    System.out.println("Received hello message response: " + res.result);
  }
});
```

## Building

```bash
./gradlew build
```

## Testing

Start a Neptulon server at local address: `127.0.0.1:3000` and then:

```bash
./gradlew check
```

## License

[MIT](LICENSE)
