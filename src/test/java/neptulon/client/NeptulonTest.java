package neptulon.client;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class NeptulonTest {
    private final String url = "ws://127.0.0.1:3000";

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertThat("hazelnuts", 3, equalTo(3));
    }

    @Test
    public void connect() {
        class Test {
            final String message;

            Test(String message) {
                this.message = message;
            }
        }

        Conn conn = new ConnImpl(url);
        conn.connect();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat("Connection was not established in time.", conn.isConnected());

        // todo: add middleware to log the incoming message here and replace logger inside response handler with verifier

        conn.sendRequest("test", new Test("wow"), new ResHandler<String>() {
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
}
