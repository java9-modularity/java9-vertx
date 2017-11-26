module easytext.web {
    requires vertx.rx.java2;
    requires easytext.pagefetch;
    requires easytext.algorithm.api;
    requires rxjava;

    uses io.vertx.reactivex.core.Vertx;
    uses javamodularity.easytext.pagefetch.WikipediaFetcher;
    uses javamodularity.easytext.algorithm.api.Analyzer;
}