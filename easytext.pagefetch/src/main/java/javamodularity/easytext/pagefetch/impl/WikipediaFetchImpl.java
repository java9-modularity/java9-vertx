package javamodularity.easytext.pagefetch.impl;

import io.reactivex.Single;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;
import javamodularity.easytext.pagefetch.WikipediaFetcher;

import java.util.ServiceLoader;

public class WikipediaFetchImpl implements WikipediaFetcher {

    private final WebClient client;

    public WikipediaFetchImpl() {
        var vertx = ServiceLoader.load(Vertx.class).findFirst().get();
        client = WebClient.create(vertx);
    }

    @Override
    public Single<String> getText(String topic) {
        var url = "https://en.wikipedia.org/w/index.php?action=raw&title=" + topic;

        return client.getAbs(url).rxSend()
            .map(HttpResponse::bodyAsString);

    }
}
