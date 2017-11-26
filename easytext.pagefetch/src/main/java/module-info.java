import javamodularity.easytext.pagefetch.WikipediaFetcher;
import javamodularity.easytext.pagefetch.impl.WikipediaFetchImpl;

module easytext.pagefetch {
    requires transitive reactive.streams;

    requires vertx.rx.java2;
    requires vertx.web.client;
    requires rxjava;
    exports javamodularity.easytext.pagefetch;

    uses io.vertx.reactivex.core.Vertx;
    provides WikipediaFetcher with WikipediaFetchImpl;

}