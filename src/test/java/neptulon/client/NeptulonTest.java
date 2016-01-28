package neptulon.client;

import neptulon.client.middleware.Logger;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class NeptulonTest {
    public static final String URL = "ws://127.0.0.1:3000";

    @Test
    public void connect() throws InterruptedException {
        if (isTravis()) {
            return;
        }

        class Test {
            final String message;

            Test(String message) {
                this.message = message;
            }
        }

        Conn conn = new ConnImpl(URL);
        conn.connect();

        Thread.sleep(100);
        assertThat("Connection was not established in time.", conn.isConnected());

        conn.middleware(new Logger());

        conn.sendRequest("echo", new Test("Hello from Java client!"), new ResHandler<String>() {
            @Override
            public Class<String> getType() {
                return String.class;
            }

            @Override
            public void handler(Response<String> res) {
                System.out.println("Received response: " + res.result);
            }
        });
    }

    private boolean isTravis() {
        return System.getenv().containsKey("TRAVIS");
    }
}
