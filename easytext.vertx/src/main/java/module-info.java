module easytext.vertx {
    requires transitive vertx.rx.java2;

    provides io.vertx.reactivex.core.Vertx with javamodularity.easytext.vertx.VertxProvider;

}