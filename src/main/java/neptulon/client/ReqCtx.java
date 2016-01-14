package neptulon.client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

/**
 * Request context.
 */
public abstract class ReqCtx {
    final String id;
    final String method;
    final JsonElement params;
    private final List<Middleware> middleware;
    private int mwIndex;
    private final Gson gson;

    protected Response response;

    protected ReqCtx(String id, String method, JsonElement params, List<Middleware> middleware, Gson gson) {
        this.id = id;
        this.method = method;
        this.params = params;
        this.middleware = middleware;
        this.gson = gson;
    }

    protected <T> T getParams(Class<T> classOfT) {
        return gson.fromJson(params, classOfT);
    }

    protected void next() {
        mwIndex++;

        if (mwIndex <= middleware.size()) {
            middleware.get(mwIndex-1).handler(this);
        }
    }
}
