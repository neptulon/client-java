package neptulon.client.middleware;

import neptulon.client.Middleware;
import neptulon.client.ReqCtx;

/**
 * Neptulon request/response logger middleware.
 */
public class Logger implements Middleware {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.class.getSimpleName());

    @Override
    public void handler(ReqCtx ctx) {
        logger.info(ctx.getParams(String.class));
    }
}
