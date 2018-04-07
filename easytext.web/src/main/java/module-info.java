module easytext.web {
    requires vertx.rx.java2;
    requires easytext.pagefetch;
    requires easytext.algorithm.api;
    requires io.reactivex.rxjava2;
    requires vertx.core;

    uses io.vertx.reactivex.core.Vertx;
    uses javamodularity.easytext.pagefetch.WikipediaFetcher;
    uses javamodularity.easytext.algorithm.api.Analyzer;
}