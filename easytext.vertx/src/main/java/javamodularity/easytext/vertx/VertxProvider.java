package javamodularity.easytext.vertx;

import io.vertx.reactivex.core.Vertx;

public class VertxProvider {
    private final static Vertx vertx = Vertx.vertx();

    public static Vertx provider() {
        return vertx;
    }
}
